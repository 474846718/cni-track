package com.cni.matcher;


import com.cni.httptrack.TrackChannel;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 单号匹配器
 * 能持有多个渠道
 */
public class Matchers {

    private List<TrackChannel> trackChannels = new ArrayList<>();

    public List<TrackChannel> getTrackChannels() {
        return trackChannels;
    }

    public void setTrackChannels(List<TrackChannel> trackChannels) {
        this.trackChannels = trackChannels;
    }

    /**
     * 通过单号 匹配渠道
     *
     * @param orderNum 被匹配单号
     * @return 匹配到的渠道
     */
    public List<TrackChannel> matchOrderNumber(String orderNum) {
        return trackChannels.stream()
                .filter(channel -> channel.getOrderNumMatcher().match(orderNum))
                .collect(Collectors.toList());
    }

    /**
     * 通过标记获取追踪渠道
     *
     * @param tag 标记
     * @return 追踪渠道
     */
    public TrackChannel getTrackChannel(String tag) {
        Optional<TrackChannel> trackChannel = trackChannels.stream().filter(channel -> tag.equals(channel.getTag())).findFirst();
        return trackChannel.orElse(null);
    }

    /**
     * 添加可匹配的渠道
     *
     * @param channels 添加渠道
     */
    public void add(TrackChannel channels) {
        this.trackChannels.add(channels);
    }


}