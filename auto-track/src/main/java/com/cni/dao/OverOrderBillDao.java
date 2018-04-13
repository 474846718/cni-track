package com.cni.dao;

import com.cni.dao.entity.OverOrderBill;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface OverOrderBillDao {

    /**
     * 通过参数找出归档表已经存在的单号
     */
    void upsertLatestInfoNodeDate(List<OverOrderBill> overOrderBills);
}

