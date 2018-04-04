package com.cni.job;


import com.cni.dao.OrderBillDao;
import com.cni.dao.OverOrderBillDao;
import com.cni.dao.entity.OrderBill;
import com.cni.dao.entity.OverOrderBill;
import com.cni.httptrack.OrderTracker;
import com.cni.httptrack.TrackChannel;
import com.cni.matcher.Matchers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;

/**
 * 任务类
 * 的方法和调度器绑定
 * 这些任务和运单相关
 */

public class OrderBillJobs {
    private static final Logger logger = LoggerFactory.getLogger(OrderBillJobs.class);

    private OrderBillDao orderBillDao;
    private OverOrderBillDao overOrderBillDao;
    private OrderTracker orderTracker;
    private Matchers matchers;

    public Matchers getMatchers() {
        return matchers;
    }

    public void setMatchers(Matchers matchers) {
        this.matchers = matchers;
    }

    public OrderBillDao getOrderBillDao() {
        return orderBillDao;
    }

    public void setOrderBillDao(OrderBillDao orderBillDao) {
        this.orderBillDao = orderBillDao;
    }

    public OverOrderBillDao getOverOrderBillDao() {
        return overOrderBillDao;
    }

    public void setOverOrderBillDao(OverOrderBillDao overOrderBillDao) {
        this.overOrderBillDao = overOrderBillDao;
    }

    public OrderTracker getOrderTracker() {
        return orderTracker;
    }

    public void setOrderTracker(OrderTracker orderTracker) {
        this.orderTracker = orderTracker;
    }

    /**
     * 重新映射运单最新的状态
     * 然后检查活跃表的完结状态的运单
     * 并且转存到归档表
     */
    public synchronized void restoreOverOrders() {
        List<OrderBill> overOrderBill = orderBillDao.findAndRemoveAccomplishOrder();
        System.out.println("活跃表将要归档的长度" + overOrderBill.size());
        overOrderBillDao.insert(overOrderBill.stream().map(OverOrderBill::new).collect(Collectors.toList()));
    }

    /**
     * 获取长时间无更新运单
     * 并且取消追踪
     */
    public synchronized void checkExpiredOrders() {
        List<String> expired = orderBillDao.findLongTimeNoUpdateOrder();
        logger.warn("获取长时间无更新的订单" + expired.size());
    }

    //TODO 后期优化不通过正则匹配识别渠道 通过数据库标记

    /**
     * 追踪活跃表的运单
     */
    public synchronized void autoTrackOrders() {
        List<String> onTrackNums = orderBillDao.findAllNumber();
        ArrayBlockingQueue<OrderBill> queue = new ArrayBlockingQueue<>(onTrackNums.size());
        for (String num : onTrackNums) {
            List<TrackChannel> channels = matchers.matchOrderNumber(num);
            TrackChannel channel = channels.get(0);
            orderTracker.startTrack(num, queue, channel);
        }
    }
}