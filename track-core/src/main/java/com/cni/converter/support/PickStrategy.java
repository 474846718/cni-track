package com.cni.converter.support;

import com.cni.statemap.MapResult;

/**
 * 当findMapping方法返回不止一个
 * 需要调用者给定挑选策略
 */
public interface PickStrategy {
    /**
     * 通过给定条件
     *
     * @param currStatus 被映射状态
     * @param optional   附加信息
     * @return 从多个结果中选取一个合适的
     */
    MapResult pick(String currStatus, Object[] optional);
}