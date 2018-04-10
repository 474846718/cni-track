package com.cni.dao;

import com.cni.dao.entity.OverOrderBill;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface OverOrderBillDao  {

    /**
     * 通过参数找出归档表已经存在的单号
     */
    List<String> findByLatestInfoNodeDate(List<OverOrderBill> overOrderBills);

    /**
     * 插入所有
     * @param overOrderBills 归档运单实体
     * @return
     */
    void insert(List<OverOrderBill> overOrderBills);
}

