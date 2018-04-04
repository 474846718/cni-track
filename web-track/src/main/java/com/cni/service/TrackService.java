package com.cni.service;

import com.cni.dao.entity.OrderBill;
import com.cni.httptrack.OrderTracker;
import com.cni.httptrack.TrackChannel;
import com.cni.matcher.Matchers;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 查单业务实现
 * <p>
 * Created by CNI on 2018/1/18.
 */
@Service
public class TrackService {

    private static final Log log = LogFactory.getLog(OrderBill.class);
    private Matchers matchers;
    private OrderTracker orderTracker;


    @Autowired
    public TrackService(OrderTracker orderTracker, Matchers matchers) {
        this.matchers = matchers;
        this.orderTracker = orderTracker;
    }

    //TODO countlatch改写
    public List<OrderBill> trackOrders(List<String> numbers) {
        BlockingQueue<OrderBill> queue = new ArrayBlockingQueue<>(numbers.size());
        numbers.forEach(num -> trackOne(num, queue));
        List<OrderBill> list = new ArrayList<>(numbers.size());
        try {
            for (int i = 0; i < numbers.size(); i++)
                list.add(queue.poll(30, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            numbers.removeAll(list.stream()
                    .map(OrderBill::getNumber)
                    .collect(Collectors.toSet()));
            log.warn("放弃剩余" + numbers);
            list.addAll(numbers.stream().map(OrderBill::error).collect(Collectors.toList()));
        }
        return list;
    }


    //TODO 整合匹配规则到Track内部
    /**
     * @param orderNum 订单号
     */
    private void trackOne(String orderNum, BlockingQueue<OrderBill> queue) {
        List<TrackChannel> matchedTrackChannels = matchers.matchOrderNumber(orderNum);
        if (CollectionUtils.isEmpty(matchedTrackChannels))
            queue.add(OrderBill.error(orderNum));
        TrackChannel trackChannel = matchedTrackChannels.get(0);
        //TODO 处理多家匹配
        orderTracker.startTrack(orderNum, queue, trackChannel);

    }

}
