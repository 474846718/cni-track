package com.cni.dao;

import com.cni.dao.entity.OrderBill;

import java.util.List;
//TODO 尝试用Repository重写DAO层！

/**
 * 活跃表的订单追踪
 */
public interface OrderBillDao{

    /**
     * 尝试插入或者更新
     * @param document
     */
    void upsert(OrderBill document);

    /**
     * 获取所有单号
     * @return
     */
    List<String> findAllNumber();

    /**
     * 通过单号
     */
    List<OrderBill> findFirstScnasDateByNumbers(String... nums);

    /**
     * 查询完结状态订单
     */
    List<OrderBill> findAndRemoveAccomplishOrder();

    /**
     * 返回长时间没有更新的单号
     */
    List<String> findLongTimeNoUpdateOrder();
}
