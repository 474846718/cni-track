package com.cni.dao;

import com.cni.dao.entity.OntrackWaybill;
import com.cni.dao.entity.Waybill;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
//TODO 尝试用Repository重写DAO层！

/**
 * 活跃表的订单追踪
 */

@Repository
public interface OntrackWaybillRepository extends MongoRepository<OntrackWaybill,String> {


    List<OntrackWaybill> findByNumberIn(List<String> numbers);


    void deleteByNumberIn(List<String> numbers);

    @Query(value = "",fields = "")
    List<OntrackWaybill> findByLatestDateLessThan(long date);
}
