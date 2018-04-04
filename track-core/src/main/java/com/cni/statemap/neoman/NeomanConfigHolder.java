package com.cni.statemap.neoman;


import com.cni.httptrack.support.Channels;
import com.cni.statemap.MapConfigHolder;
import com.cni.statemap.MapResult;
import com.cni.statemap.MapRow;
import okhttp3.OkHttpClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * 钮门系统提供配置信息的配置持有类
 */
public class NeomanConfigHolder implements MapConfigHolder {
    private static final Log log = LogFactory.getLog(NeomanConfigHolder.class);
    private static final String authorization = "aW50ZXJuYWwwMDE6WmpRNU5qbG1Zek0yTmpBME5qVmhNakU0T1RrMU1HSTBNamRrT1RBNE1UVT0=";
    private List<MapRow> currMapRows = Collections.synchronizedList(new ArrayList<>());
    private OkHttpClient client;

    private class MatchType {
        static final int CONSTANT = 1;
        static final int REGEX = 2;
    }

    private interface GetRemoteConfig {
        @GET("/v1.0.0/statusConfig/findByChannelName")
        Call<StateMappingResponseBody> findByChannelName(@Query("channelName") String channelName,
                                                         @Header("Authorization") String authorization);
    }

    public NeomanConfigHolder(OkHttpClient client) {
        this.client = client;
        try {
            init();
        } catch (IOException e) {
            System.err.println("从网络获取配置失败！");
            e.printStackTrace();
        }
    }

    /**
     * 从网络读取配置信息
     * 全部重载
     *
     * @throws IOException
     */
    @Override
    public void init() throws IOException {
        currMapRows.clear();
        GetRemoteConfig getRemoteConfig = new Retrofit.Builder()
                .baseUrl("http://tracking-info.cnilink.com:9090/")
                .addConverterFactory(JacksonConverterFactory.create())
                .client(client)
                .build()
                .create(GetRemoteConfig.class);
        String[] allChannel = new String[]{Channels.BLUEDART, Channels.DELHIVERY, Channels.ECOM, Channels.GATI, Channels.INPT};
        for (String channel : allChannel) {
            Response<StateMappingResponseBody> response = getRemoteConfig.findByChannelName(channel, authorization).execute();
            List<MapRow> configList = Objects.requireNonNull(response.body()).getInfo();
            currMapRows.addAll(configList);
        }
        log.warn("添加配置:");
        currMapRows.forEach(log::warn);
    }

    /**
     * 按照参数重载配置
     *
     * @return
     */
    @Override
    public void reload(List<MapRow> configs) {
        currMapRows.clear();
        currMapRows.addAll(configs);
    }

    /**
     * 查询并且返回docs 里面符合条件的状态
     * @param currState 要被映射的状态
     * @param flow 查询条件 派送件还是退件
     * @param compName 公司名字
     * @return 所有匹配项
     *
     */
    @Override
    public List<MapResult> getMapResults(String currState, String flow, String compName) {
        return currMapRows.stream()
                .filter(doc -> compName.equals(doc.getChannelName()))
                .filter(doc -> flow.equals(doc.getFlowType()))
                .filter(doc -> {
                    if (MatchType.CONSTANT == (doc.getMatchingType())) //常量匹配 忽略大小写
                        return currState.trim().equalsIgnoreCase(doc.getMatchingStatus().trim());
                    else
                        return MatchType.REGEX == doc.getMatchingType() &&  //正则匹配
                                currState.matches(doc.getMatchingStatus());
                })
                .map(doc -> new MapResult(doc.getStatusName(), doc.getReplaceStatus()))
                .collect(Collectors.toList());
    }

    /**
     * 按照StatusMatchingId增量更新
     * @param MapRows 在线配置信息
     */
    @Override
    public void addMapRow(List<MapRow> MapRows) {
        removeMapRows(MapRows);
        currMapRows.addAll(MapRows);
    }

    /**
     * @param mapRows 一条映射配置信息实体类
     */
    @Override
    public void removeMapRows(List<MapRow> mapRows) {
        mapRows.forEach(config -> removeMapRow(config.getStatusConfigId()));
    }

    /**
     * @param statusMatchingId 一条映射配置实例的id
     */
    @Override
    public void removeMapRow(long statusMatchingId) {
        currMapRows.removeIf(checkedRow -> statusMatchingId == checkedRow.getStatusMatchingId());
    }

    @Override
    public List<MapRow> getCurrMapRows() {
        return currMapRows;
    }


}
