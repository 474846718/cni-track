package com.cni.statemap;

import java.io.IOException;
import java.util.List;

/**
 * 运单状态映射配置信息持有者
 * 负责提供配置信息的读取和使用方式
 */
public interface MapConfigHolder {
    //TODO 对接自己的系统
    /**
     * 从网络或者数据库初始化
     * @throws IOException
     */
    void init() throws IOException;

    /**
     * 通过参数重载配置信息
     * @param configs
     */
    void reload(List<MapRow> configs);

    /**
     *
     * @param currState 要被映射的状态
     * @param flow 查询条件 派送件还是退件
     * @param compName 公司名字
     * @return 查询匹配的所有结果
     */
    List<MapResult> getMapResults(String currState, String flow, String compName);

    /**
     *
     * @param MapRows
     */
    void addMapRow(List<MapRow> MapRows);

    void removeMapRows(List<MapRow> MapRows);

    void removeMapRow(long statusMatchingId);

    List<MapRow> getCurrMapRows();
}
