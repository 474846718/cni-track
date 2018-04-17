package com.cni.converter.support;

import com.alibaba.fastjson.JSON;
import com.cni.converter.NeomanConverter;
import com.cni.dao.entity.Waybill;
import com.cni.exception.NeomanException;
import com.cni.httptrack.resp.NeomanResponseBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * 钮门拼单的连接器
 * 查询钮门系统的运单
 */
@Component
public class NeomanJoint {
    private static final Log log = LogFactory.getLog(NeomanJoint.class);
    private OkHttpClient client;
    private NeomanConverter neomanConverter;
    private HttpUrl baseUrl;

    /**
     * @param client http客户端
     */
    public NeomanJoint(OkHttpClient client) {
        this.client = client;
        this.neomanConverter = new NeomanConverter();
    }

    public HttpUrl getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(HttpUrl baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * 合并纽门查单信息
     * @param body 五家公司原生的API查询的结果
     * @return 返回拼单后的结果
     */
    public Waybill mergeNeoman(Waybill body) throws NeomanException {
        Assert.notNull(body, "传入参数不能为空");
        HttpUrl rebuild=baseUrl.newBuilder().addPathSegment("neoman").addQueryParameter("orderNum",body.getNumber()).build();
        Request request =new Request.Builder().url(rebuild).get().build();
        Waybill neomanWaybill = null;
        try {
            ResponseBody responseBody = client.newCall(request).execute().body();
            NeomanResponseBody in = JSON.parseObject(responseBody.string(), NeomanResponseBody.class);
            try {
                neomanWaybill = neomanConverter.convert(in);
            } catch (Exception e) {
                log.warn("钮门格式转换出错");
                throw new NeomanException("钮门拼单出错！");
            }
        } catch (Exception e) {
            log.warn("链接时纽曼系统出错！");
            throw new NeomanException("钮门拼单出错！");
        }
        body.joinInfoNode(neomanWaybill);
        return body;
    }
}
