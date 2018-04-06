package com.cni.dao;

import com.cni.dao.entity.OrderBill;

import java.util.List;
//TODO 尝试用Repository重写DAO层！

/**
 * 活跃表的订单追踪
 */
public interface OrderBillDao {

    /**
     * 尝试插入或者更新
     *
     * @param document
     */
    void upsert(OrderBill document);

    /**
     * 获取所有单号
     *
     * @return
     */
    List<String> findAllNumber();


    /**
     *
     * @return 所有运单
     */
    List<OrderBill> findAllNumberAndTailCompany();

    /**
     * 通过单号
     */
    List<OrderBill> findFirstScansDateByNumbers(List<String> nums);

    /**
     * 查询完结状态运单
     */
    List<OrderBill> findOverOrderBill();

    /**
     * 删除完结状态运单
     *
     * @param nums 要删除的单号
     */
    void removeOverOrderBill(List<String> nums);

    /**
     * 返回长时间没有更新的单号
     */
    List<String> findExpiredOrderBill();
}
