package com.cni.httptrack;

import com.alibaba.fastjson.JSON;
import com.cni.converter.Converter;
import com.cni.converter.DelhiveryConverter;
import com.cni.converter.NeomanConverter2;
import com.cni.converter.support.NeomanJoint;
import com.cni.dao.OrderBillDao;
import com.cni.dao.entity.OrderBill;
import com.cni.exception.ConvertException;
import com.cni.exception.NeomanException;
import com.cni.exception.OrderNotFoundException;
import com.cni.httptrack.resp.NeomanResponseBody;
import com.cni.matcher.Matchers;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
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
public class OrderTracker {
    private static final Logger log = LoggerFactory.getLogger(OrderTracker.class);

    private NeomanJoint neomanJoint;
    private OkHttpClient client;
    private String scheme;
    private String host;
    private int port;
    private String version;
    private HttpUrl baseUrl;
    private OrderBillDao orderBillDao;
    private SelfDispatchNumHolder selfDispatchNumHolder;
    private Matchers matchers;


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

    public OrderBillDao getOrderBillDao() {
        return orderBillDao;
    }

    public void setOrderBillDao(OrderBillDao orderBillDao) {
        this.orderBillDao = orderBillDao;
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
     * @throws Exception 查单失败 转换格式失败 纽曼系统拼单都会抛异常
     */
    public void startTrackRet(String orderNum, BlockingQueue<OrderBill> queue, TrackChannel trackChannel) {


        Assert.notNull(client, "没有提供http客户端");
        Converter converter = trackChannel.getConverter();

        //TODO 以后可以删掉
        if (converter instanceof DelhiveryConverter && selfDispachOnDel(orderNum, queue, trackChannel))
            return;
        //正常处理流程
        HttpUrl rebuildUrl = baseUrl.newBuilder().addPathSegment(trackChannel.getUrlSegment()).addQueryParameter("orderNum", orderNum).build();
        Request request = new Request.Builder().url(rebuildUrl).get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    ResponseBody responseBody = response.body();
                    Assert.notNull(responseBody, "返回值为空");
                    String res = responseBody.string();
                    Object in = JSON.parseObject(res, converter.getTypeConvertBefore());
                    OrderBill orderBill = converter.convert(in);

                    try {
                        try {
                            orderBill = neomanJoint.mergeNeoman(orderBill);
                        } catch (NeomanException ignored) {
                            log.warn("钮门拼单失败：" + orderNum);
                        }
                        orderBillDao.upsert(orderBill);
                        log.warn("查单成功！已存数据库：" + orderNum);
                        queue.add(orderBill);
//                        putRedisCache(orderNum, orderBill, 15);
                    } catch (RuntimeException ignored) {
                        log.warn("插入Mongodb失败：" + orderNum);
                        enqueueErrorBill();
                    }
                } catch (ConvertException e) {
                    log.warn("运单转换时出错：" + orderNum, e);
                    enqueueErrorBill();
                } catch (IOException e) {
                    log.warn("读取渠道http响应体字符串失败:" + orderNum, e);
                    enqueueErrorBill();
                } catch (OrderNotFoundException ignored) {
                    log.warn("查无此单:" + orderNum);
                    enqueueErrorBill();
                } catch (RuntimeException e) {
                    log.warn("追踪时发生其他异常:" + orderNum, e);
                    enqueueErrorBill();
                }
            }

            private void enqueueErrorBill() {
                OrderBill orderBill = OrderBill.error(orderNum);
                queue.add(orderBill);
            }
        });
    }

    /**
     * 异步追踪一群单号
     * 超过30秒放弃追踪
     *
     * @param orderNums 单号
     */
    public List<OrderBill> startTrackRet(List<String> orderNums) {
        BlockingQueue<OrderBill> queue = new ArrayBlockingQueue<>(orderNums.size());
        for (String num : orderNums) {
            List<TrackChannel> matchedTrackChannels = matchers.matchOrderNumber(num);
            if (CollectionUtils.isEmpty(matchedTrackChannels))
                queue.add(OrderBill.error(num));
            else {
                TrackChannel trackChannel = matchedTrackChannels.get(0); //TODO 处理多家匹配
                startTrackRet(num, queue, trackChannel);
            }
        }
        return obtainFromQueue(queue, orderNums);
    }


    public void startTrack(List<String> orderNums) {
        for (String num : orderNums) {
            List<TrackChannel> matchedTrackChannels = matchers.matchOrderNumber(num);
            if (CollectionUtils.isEmpty(matchedTrackChannels))
                continue;
            TrackChannel trackChannel = matchedTrackChannels.get(0); //TODO 处理多家匹配
            startTrack(num, trackChannel);
        }
    }

    /**
     * 不收集结果的 查单
     * 只存mongodb
     *
     * @param orderNum     运单
     * @param trackChannel 追踪渠道
     */
    public void startTrack(String orderNum, TrackChannel trackChannel) {
        Assert.notNull(client, "没有提供http客户端");
        Converter converter = trackChannel.getConverter();

        //TODO 以后可以删掉
        if (converter instanceof DelhiveryConverter && selfDispachOnDel(orderNum, new ArrayBlockingQueue<>(1), trackChannel))
            return;
        //正常处理流程
        HttpUrl rebuildUrl = baseUrl.newBuilder().addPathSegment(trackChannel.getUrlSegment()).addQueryParameter("orderNum", orderNum).build();
        Request request = new Request.Builder().url(rebuildUrl).get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                log.warn("请求失败", e);
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    ResponseBody responseBody = response.body();
                    Assert.notNull(responseBody, "返回值为空");
                    String res = responseBody.string();
                    Object in = JSON.parseObject(res, converter.getTypeConvertBefore());
                    OrderBill orderBill = converter.convert(in);

                    try {
                        try {
                            orderBill = neomanJoint.mergeNeoman(orderBill);
                        } catch (NeomanException ignored) {
                            log.warn("钮门拼单失败：" + orderNum);
                        }
                        orderBillDao.upsert(orderBill);
                        log.warn("查单成功！已存数据库：" + orderNum);
                    } catch (RuntimeException ignored) {
                        log.warn("插入Mongodb失败：" + orderNum);
                    }
                } catch (ConvertException e) {
                    log.warn("运单转换时出错：" + orderNum, e);
                } catch (IOException e) {
                    log.warn("读取渠道http响应体字符串失败:" + orderNum, e);

                } catch (OrderNotFoundException ignored) {
                    log.warn("查无此单:" + orderNum);

                } catch (RuntimeException e) {
                    log.warn("追踪时发生其他异常:" + orderNum, e);

                }
            }
        });
    }

    /**
     * 指定渠道追踪一群单号
     * 只存数据库不返回
     *
     * @param orderNums 单号
     */
    public void startTrack(List<String> orderNums, TrackChannel channel) {
        orderNums.forEach(num -> startTrack(num, channel));
    }


    /**
     * 从队列获取结果
     * 超过30秒则放弃
     *
     * @return 运单数据
     */
    private List<OrderBill> obtainFromQueue(BlockingQueue<OrderBill> queue, List<String> orderNums) {
        List<OrderBill> list = new ArrayList<>(orderNums.size());
        try {
            for (int i = 0; i < orderNums.size(); i++)
                list.add(queue.poll(30, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            orderNums.removeAll(list.stream()
                    .map(OrderBill::getNumber)
                    .collect(Collectors.toSet()));
            log.warn("放弃剩余" + orderNums);
            list.addAll(orderNums.stream().map(OrderBill::error).collect(Collectors.toList()));
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
    private boolean selfDispachOnDel(String orderNum, BlockingQueue<OrderBill> queue, TrackChannel trackChannel) {
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
                    OrderBill orderBill = neomanConverter2.convert(neomanResponseBody);

                    try {
                        orderBillDao.upsert(orderBill);
                        log.warn("查单成功！已存数据库：" + orderNum);
                        queue.add(orderBill);
//                        putRedisCache(orderNum, orderBill, 15);
                    } catch (RuntimeException ignored) {
                        log.warn("插入Mongodb失败：" + orderNum);
                        enqueueErrorBill(false);
                    }
                } catch (ConvertException e) {
                    log.warn("运单转换时出错：" + orderNum, e);
                    enqueueErrorBill(true);
                } catch (IOException e) {
                    log.warn("读取渠道http响应体字符串失败:" + orderNum, e);
                    enqueueErrorBill(false);
                } catch (OrderNotFoundException ignored) {
                    log.warn("查无此单:" + orderNum);
                    enqueueErrorBill(true);
                } catch (RuntimeException e) {
                    log.warn("追踪时发生其他异常:" + orderNum, e);
                    enqueueErrorBill(false);
                }
            }

            private void enqueueErrorBill(boolean isCache) {
                OrderBill orderBill = OrderBill.error(orderNum);
                queue.add(orderBill);
                if (isCache) {
//                    putRedisCache(orderNum, orderBill, 10);
                }
            }
        });

        return true;
    }
}


