package com.cni.dao;

import com.cni.dao.entity.CompleteWaybill;
import com.cni.dao.entity.Waybill;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompleteWaybillRepository extends MongoRepository<CompleteWaybill, String> {
    /**
     * 时间距今超过一个月
     */
    List<CompleteWaybill> findByLatestDateLessThan( long date);

    List<CompleteWaybill> findByNumberIn(List<String> numbers);
}
