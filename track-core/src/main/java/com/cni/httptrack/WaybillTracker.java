package com.cni.httptrack;

import com.alibaba.fastjson.JSON;
import com.cni.converter.Converter;
import com.cni.converter.DelhiveryConverter;
import com.cni.converter.NeomanConverter2;
import com.cni.converter.support.NeomanJoint;
import com.cni.dao.CompleteWaybillRepository;
import com.cni.dao.OntrackWaybillRepository;
import com.cni.dao.entity.CompleteWaybill;
import com.cni.dao.entity.OntrackWaybill;
import com.cni.dao.entity.Waybill;
import com.cni.exception.ConvertException;
import com.cni.exception.OrderNotFoundException;
import com.cni.httptrack.resp.NeomanResponseBody;
import com.cni.matcher.Matchers;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.AsyncClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StreamUtils;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 运单追踪器
 * 包装了http客户端和钮门系统连接器
 * 使用渠道类进行追踪
 * 提供redis缓存和mongodb持久化功能
 */
public class WaybillTracker {
    private static final Logger log = LoggerFactory.getLogger(WaybillTracker.class);

    private NeomanJoint neomanJoint;
    private OkHttpClient client;
    private String scheme;
    private String host;
    private int port;
    private String version;
    private HttpUrl baseUrl;
    private OntrackWaybillRepository ontrackWaybillRepository;
    private CompleteWaybillRepository completeWaybillRepository;
    private SelfDispatchNumHolder selfDispatchNumHolder;
    private Matchers matchers;

    public OntrackWaybillRepository getOntrackWaybillRepository() {
        return ontrackWaybillRepository;
    }

    public void setOntrackWaybillRepository(OntrackWaybillRepository ontrackWaybillRepository) {
        this.ontrackWaybillRepository = ontrackWaybillRepository;
    }

    public CompleteWaybillRepository getCompleteWaybillRepository() {
        return completeWaybillRepository;
    }

    public void setCompleteWaybillRepository(CompleteWaybillRepository completeWaybillRepository) {
        this.completeWaybillRepository = completeWaybillRepository;
    }

    public SelfDispatchNumHolder getSelfDispatchNumHolder() {
        return selfDispatchNumHolder;
    }

    public void setSelfDispatchNumHolder(SelfDispatchNumHolder selfDispatchNumHolder) {
        this.selfDispatchNumHolder = selfDispatchNumHolder;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setClient(OkHttpClient client) {
        this.client = client;
    }

    public Matchers getMatchers() {
        return matchers;
    }

    public void setMatchers(Matchers matchers) {
        this.matchers = matchers;
    }


    /**
     * 异步的方式进行查单，适合多号查询的情况
     * 保证返回正常的单号 否则抛异常
     *
     * @param orderNum     单号
     * @param trackChannel 查询渠道
     */
    public void startTrackRet(String orderNum, BlockingQueue<Waybill> queue, TrackChannel trackChannel) {


        Assert.notNull(client, "没有提供http客户端");
        Converter converter = trackChannel.getConverter();

        //TODO 以后可以删掉
        if (converter instanceof DelhiveryConverter && selfDispachOnDel(orderNum, queue, trackChannel))
            return;

        //正常处理流程
        HttpUrl rebuildUrl = baseUrl.newBuilder().addPathSegment(trackChannel.getUrlSegment()).addQueryParameter("orderNum", orderNum).build();
        Request request = new Request.Builder().url(rebuildUrl).get().build();

        OkHttp3ClientHttpRequestFactory factory=new OkHttp3ClientHttpRequestFactory();
        AsyncClientHttpRequest asyncClientHttpRequest =factory.createAsyncRequest(URI.create(request.toString()),HttpMethod.GET);
        try {
            ListenableFuture<ClientHttpResponse> future = asyncClientHttpRequest.executeAsync();
            future.addCallback(result -> {
                try {
                    String res = StreamUtils.copyToString(result.getBody(),Charset.defaultCharset());
                    Object in = JSON.parseObject(res, converter.getTypeConvertBefore());
                    Waybill orderBill = converter.convert(in);
                    try {
                        saveToRepository(orderBill);
                        log.warn("查单成功！已存数据库：" + orderNum);
                    } catch (RuntimeException ignored) {
                        log.warn("插入Mongodb失败：" + orderNum);
                    }finally {
                        queue.add(orderBill);
                    }
                } catch (ConvertException e) {
                    log.warn("运单转换时出错：" + orderNum, e);
                } catch (IOException e) {
                    log.warn("读取渠道http响应体字符串失败:" + orderNum, e);
                } catch (OrderNotFoundException ignored) {
                    log.warn("查无此单:" + orderNum);
                } catch (RuntimeException e) {
                    log.warn("追踪时发生其他异常:" + orderNum, e);
                }finally {
                    queue.add(Waybill.error(orderNum));
                }
            }, ex -> log.warn("链接失败"));
        } catch (IOException e) {
            log.warn("链接失败");
            e.printStackTrace();
        }
    }

    /**
     * 异步追踪一群单号
     * 超过30秒放弃追踪
     *
     * @param orderNums 单号
     */
    public List<Waybill> startTrackRet(List<String> orderNums) {
        BlockingQueue<Waybill> queue = new ArrayBlockingQueue<>(orderNums.size());
        for (String num : orderNums) {
            List<TrackChannel> matchedTrackChannels = matchers.matchOrderNumber(num);
            if (CollectionUtils.isEmpty(matchedTrackChannels))
                queue.add(Waybill.error(num));
            else {
                TrackChannel trackChannel = matchedTrackChannels.get(0); //TODO 处理多家匹配
                startTrackRet(num, queue, trackChannel);
            }
        }
        return obtainFromQueue(queue, orderNums);
    }




    private void saveToRepository(Waybill waybill) {
        if (Waybill.COMPLETE_STATUS.contains(waybill.getLatestStatus()))
            completeWaybillRepository.save(JSON.parseObject(JSON.toJSONString(waybill), CompleteWaybill.class));
        else
            ontrackWaybillRepository.save(JSON.parseObject(JSON.toJSONString(waybill), OntrackWaybill.class));
    }


    /**
     * 从队列获取结果
     * 超过30秒则放弃
     *
     * @return 运单数据
     */
    private List<Waybill> obtainFromQueue(BlockingQueue<Waybill> queue, List<String> orderNums) {
        List<Waybill> list = new ArrayList<>(orderNums.size());
        try {
            for (int i = 0; i < orderNums.size(); i++)
                list.add(queue.poll(30, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            orderNums.removeAll(list.stream()
                    .map(Waybill::getNumber)
                    .collect(Collectors.toSet()));
            log.warn("放弃剩余" + orderNums);
            list.addAll(orderNums.stream().map(Waybill::error).collect(Collectors.toList()));
        }
        return list;
    }


    @PostConstruct
    public void postConstruct() {
        baseUrl = new HttpUrl.Builder().scheme(scheme).host(host).port(port).addPathSegment(version).build();
        neomanJoint = new NeomanJoint(this.client);
        neomanJoint.setBaseUrl(baseUrl.newBuilder().host("www.cnilink.com").build());
    }


    /**
     * TODO 以后可以删除
     * 是否是自派送
     * 如果是就处理并返回true
     * 反则直接返回false
     *
     * @param orderNum 单号
     */
    private boolean selfDispachOnDel(String orderNum, BlockingQueue<Waybill> queue, TrackChannel trackChannel) {
        boolean isSelfDispatch = selfDispatchNumHolder.has(orderNum);
        if (!isSelfDispatch)
            return false;

        log.warn("自派件处理:" + orderNum);
        //追踪delhivery自派件
        HttpUrl neoman = new HttpUrl.Builder().scheme("http").host("www.cnilink.com").port(9999)
                .addPathSegment("v1.0.0").addPathSegment("neoman").addQueryParameter("orderNum", orderNum)
                .build();
        Request request = new Request.Builder().url(neoman).get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    ResponseBody responseBody = response.body();
                    Assert.notNull(responseBody, "返回值为空");
                    NeomanConverter2 neomanConverter2 = new NeomanConverter2(trackChannel.getConverter().getMapConfigHolder());
                    NeomanResponseBody neomanResponseBody = JSON.parseObject(responseBody.string(), neomanConverter2.getTypeConvertBefore());
                    Waybill waybill = neomanConverter2.convert(neomanResponseBody);

                    try {
                        saveToRepository(waybill);
                        log.warn("查单成功！已存数据库：" + orderNum);
                    } catch (RuntimeException ignored) {
                        log.warn("插入Mongodb失败：" + orderNum);
                    } finally {
                        queue.add(waybill);
                    }
                } catch (ConvertException e) {
                    log.warn("运单转换时出错：" + orderNum, e);

                } catch (IOException e) {
                    log.warn("读取渠道http响应体字符串失败:" + orderNum, e);

                } catch (OrderNotFoundException ignored) {
                    log.warn("查无此单:" + orderNum);
                } catch (RuntimeException e) {
                    log.warn("追踪时发生其他异常:" + orderNum, e);
                } finally {
                    queue.add(Waybill.error(orderNum));
                }
            }
        });

        return true;
    }
}


