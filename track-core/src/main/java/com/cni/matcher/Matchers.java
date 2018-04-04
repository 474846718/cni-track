package com.cni.matcher;


import com.cni.httptrack.TrackChannel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 单号匹配器
 * 能持有多个渠道
 *
 */
public class Matchers {

    private List<TrackChannel> channels = new ArrayList<>();

    /**
     * 通过单号 匹配渠道
     * @param orderNum 被匹配单号
     * @return 匹配到的渠道
     */
    public List<TrackChannel> matchOrderNumber(String orderNum) {
        return channels.stream()
                .filter(channel -> channel.getOrderNumMatcher().match(orderNum))
                .collect(Collectors.toList());
    }

    /**
     * 添加可匹配的渠道
     * @param channels 添加渠道
     */
    public void add(TrackChannel... channels) {
        this.channels.addAll(Arrays.asList(channels));
    }


}