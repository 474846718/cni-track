package com.cni.service;

import com.cni.dao.OrderBillDao;
import com.cni.dao.entity.OrderBill;
import com.cni.httptrack.OrderTracker;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 查单业务实现
 * <p>
 * Created by CNI on 2018/1/18.
 */
@Service
public class TrackService {

    private static final Log log = LogFactory.getLog(OrderBill.class);
    private OrderTracker orderTracker;
    private OrderBillDao orderBillDao;
    private RedisTemplate<String, OrderBill> redisTemplate;


    @Autowired
    public TrackService(OrderTracker orderTracker,
                        OrderBillDao orderBillDao,
                        RedisTemplate<String, OrderBill> redisTemplate) {
        this.orderTracker = orderTracker;
        this.orderBillDao = orderBillDao;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 先从数据库查询
     * 查不到的就主动追踪单号
     * 合并结果返回
     *
     * @param numbers 单号
     * @return 查询结果
     */
    public List<OrderBill> trackOrders(List<String> numbers) {
        ValueOperations<String, OrderBill> vp = redisTemplate.opsForValue();
        List<OrderBill> redisHit = numbers.stream()
                .map(vp::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        List<String> redisHitNum = redisHit.stream()
                .map(OrderBill::getNumber)
                .collect(Collectors.toList());
        log.warn("redis命中" + redisHitNum);
        numbers.removeAll(redisHitNum);
        if (CollectionUtils.isEmpty(numbers))
            return redisHit;


        List<OrderBill> mongodbHit = orderBillDao.findById(numbers);
        List<String> mongodbHitNum = mongodbHit.stream()
                .filter(Objects::nonNull)
                .map(OrderBill::getNumber)
                .collect(Collectors.toList());
        log.warn("mongodb命中" + mongodbHitNum);
        numbers.removeAll(mongodbHitNum);
        mongodbHit.addAll(redisHit);
        if (CollectionUtils.isEmpty(numbers))
            return mongodbHit;


        List<OrderBill> trackRes = orderTracker.startTrackRet(numbers);
        List<String> trackNums=trackRes.stream().map(OrderBill::getNumber).collect(Collectors.toList());
        log.warn("网络查单" + trackNums);


        trackRes.addAll(mongodbHit);
        trackRes.forEach(track -> vp.set(track.getNumber(), track));
        return trackRes;
    }

}
