package com.cni.httptrack;

import com.cni.converter.Converter;
import com.cni.matcher.OrderNumMatcher;

/**
 * 代表不同的追踪渠道
 * 这个类和transform服务耦合
 * 提供给OrderTracker使用
 */
public class TrackChannel {
    //对应渠道的转换器
    private Converter converter;
    //对应渠道的请求路径
    private String urlSegment;
    //对应渠道的单号匹配器
    private OrderNumMatcher orderNumMatcher;
    //用于标识
    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Converter getConverter() {
        return converter;
    }

    public void setConverter(Converter converter) {
        this.converter = converter;
    }

    public String getUrlSegment() {
        return urlSegment;
    }

    public void setUrlSegment(String urlSegment) {
        this.urlSegment = urlSegment;
    }

    public OrderNumMatcher getOrderNumMatcher() {
        return orderNumMatcher;
    }

    public void setOrderNumMatcher(OrderNumMatcher orderNumMatcher) {
        this.orderNumMatcher = orderNumMatcher;
    }
}
