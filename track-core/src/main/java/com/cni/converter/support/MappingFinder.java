package com.cni.converter.support;

import com.cni.statemap.MapConfigHolder;
import com.cni.statemap.MapResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 映射查找器
 * 内部通过映射配置持有者选取策略
 */
public class MappingFinder {
    private static final Logger log = LoggerFactory.getLogger(MappingFinder.class);
    private MapConfigHolder configHolder;
    private final String channels;
    private PickStrategy strategy;  //TODO 尝试多个多个策略


    public MappingFinder(MapConfigHolder holder, String channels) {
        this.configHolder = holder;
        this.channels = channels;
    }

    public void setStrategy(PickStrategy strategy) {
        this.strategy = strategy;
    }


    /**
     * 通过给定一个代表当前运单状态的字符串
     * 根据方向和 策略参数 查找对应的映射状态
     * currStatus可以映射配置修改
     * 映射结果只有一个时直接返回
     * 有多个时调用策略对象 通过策略参数选取合适的映射
     *
     * @param currStatus 被映射的状态
     * @param flow       被映射状态的相关运单的方向
     * @param optionals  原始运单信息作为选项，必须配合strategy对象使用
     * @return 最终的映射结果
     */
    public MapResult findMapping(String currStatus, String flow, Object... optionals) {
        Assert.notNull(flow, "运单方向不能为空！");
        if (StringUtils.isEmpty(currStatus))
            return new MapResult();

        List<MapResult> results = configHolder.getMapResults(currStatus, flow, channels);
        if (!CollectionUtils.isEmpty(results))
            if (1 == results.size())
                return results.get(0);
            else if (results.size() > 1 && !ObjectUtils.isEmpty(strategy) && !ObjectUtils.isEmpty(optionals)) {
                MapResult result = strategy.pick(currStatus, optionals);
                return ObjectUtils.isEmpty(result) ? new MapResult() : result;
            } else {
//                log.warn("找到多个映射结果！无法确定！" + currStatus);
            }
        else {
//            log.warn("找不到映射结果！" + currStatus);
        }
        return new MapResult();
    }


}
