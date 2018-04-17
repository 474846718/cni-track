package com.cni.dao;

import com.cni.dao.entity.CompleteWaybill;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompleteWaybillRepository extends MongoRepository<CompleteWaybill,String> {

}
