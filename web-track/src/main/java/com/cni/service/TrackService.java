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
 * Created by CNI on 2018/1/18.
 */
@Service
public class TrackService {

    private static final Log log = LogFactory.getLog(OrderBill.class);
    private OrderTracker orderTracker;


    @Autowired
    public TrackService(OrderTracker orderTracker) {
        this.orderTracker = orderTracker;
    }


    public List<OrderBill> trackOrders(List<String> numbers) {
        return orderTracker.startTrack(numbers);
    }
}
