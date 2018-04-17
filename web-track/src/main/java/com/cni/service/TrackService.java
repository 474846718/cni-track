package com.cni.service;

import com.cni.dao.CompleteWaybillRepository;
import com.cni.dao.OntrackWaybillRepository;
import com.cni.dao.entity.CompleteWaybill;
import com.cni.dao.entity.OntrackWaybill;
import com.cni.dao.entity.Waybill;
import com.cni.httptrack.WaybillTracker;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

    private static final Log log = LogFactory.getLog(Waybill.class);
    private WaybillTracker waybillTracker;
    private CompleteWaybillRepository completeWaybillRepository;
    private OntrackWaybillRepository ontrackWaybillRepository;
    private RedisTemplate<String, Waybill> redisTemplate;


    @Autowired
    public TrackService(WaybillTracker waybillTracker,
                        CompleteWaybillRepository completeWaybillRepository,
                        OntrackWaybillRepository ontrackWaybillRepository,
                        RedisTemplate<String, Waybill> redisTemplate) {
        this.waybillTracker = waybillTracker;
        this.completeWaybillRepository = completeWaybillRepository;
        this.ontrackWaybillRepository = ontrackWaybillRepository;
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
    public List<Waybill> trackOrders(List<String> numbers) {
        ValueOperations<String, Waybill> vp = redisTemplate.opsForValue();
        List<Waybill> redisHit = numbers.stream()
                .map(vp::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        List<String> redisHitNum = redisHit.stream()
                .map(Waybill::getNumber)
                .collect(Collectors.toList());
        log.warn("redis命中" + redisHitNum);
        numbers.removeAll(redisHitNum);
        if (CollectionUtils.isEmpty(numbers))
            return redisHit;

        List<OntrackWaybill> mongodbHit = ontrackWaybillRepository.findByNumberIn(numbers);
        List<CompleteWaybill> mongodbHit2= completeWaybillRepository.findByNumberIn(numbers);


        List<String> mongodbHitNum = mongodbHit.stream()
                .filter(Objects::nonNull)
                .map(Waybill::getNumber)
                .collect(Collectors.toList());
        log.warn("mongodb命中" + mongodbHitNum);
        numbers.removeAll(mongodbHitNum);
        mongodbHit.addAll(redisHit);
        if (CollectionUtils.isEmpty(numbers))
            return mongodbHit;


        List<Waybill> trackRes = waybillTracker.startTrackRet(numbers);
        List<String> trackNums = trackRes.stream()
                .filter(Objects::nonNull)
                .map(Waybill::getNumber)
                .collect(Collectors.toList());
        log.warn("网络查单" + trackNums);

        trackRes.addAll(mongodbHit);
        trackRes.forEach(track -> vp.set(track.getNumber(), track));
        return trackRes;
    }


}
