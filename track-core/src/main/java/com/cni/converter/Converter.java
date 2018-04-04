package com.cni.converter;

import com.cni.dao.entity.OrderBill;
import com.cni.exception.ConvertException;
import com.cni.exception.OrderNotFoundException;
import com.cni.matcher.OrderNumMatcher;
import com.cni.statemap.MapConfigHolder;

/**
 * 负责把几家公司的接口数据抓换成自己的格式
 * Created by CNI on 2018/1/18.
 */
public interface Converter<T> {

    /**
     * 设置映射配置持有器
     */
    void setMapConfigHolder(MapConfigHolder configHolder);

    /**
     * 获取映射配置持有器
     * @return MapConfigHolder
     */
    MapConfigHolder getMapConfigHolder();

    /**
     * 返回这个转换器支持的pojo输入对象
     *
     * @return
     */
    Class<T> getTypeConvertBefore();

    /**
     * @param in 输入要被转换的对象
     * @return OrderBill
     * @throws OrderNotFoundException 查无此单
     * @throws ConvertException       转换时发生异常
     */
    OrderBill convert(T in) throws OrderNotFoundException, ConvertException;


    /**
     * 设置相关的单号匹配器可能后面有用
     *
     * @param matcher 单号匹配器
     */
    void setOrderNumMatcher(OrderNumMatcher matcher);
}
