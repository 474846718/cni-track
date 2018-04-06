package com.cni.dao.repository;

import com.cni.dao.entity.OverOrderBill;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OverOrderBillRepository extends MongoRepository<OverOrderBill,String> {}

