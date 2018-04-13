package com.cni.dao;

import com.cni.dao.entity.OrderBill;
import com.cni.dao.entity.OverOrderBill;
import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;
import org.bson.BSON;
import org.springframework.beans.annotation.AnnotationBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 完结运单 插入归档表
 */
@Repository
public class OverOrderBillDaoImpl implements OverOrderBillDao {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public OverOrderBillDaoImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    /**
     * 按照完结日期查询归档表
     * 获取已存有的记录
     *
     * @param overOrderBills 查询条件
     * @return 单号
     */
    public void upsertLatestInfoNodeDate(List<OverOrderBill> overOrderBills) {
        for (OverOrderBill overOrderBill : overOrderBills) {
            long date = overOrderBill.getInfoNodes().get(0).getDate();
            Query query = new Query(Criteria.where("infoNodes.0.date").is(date));
            Update update = Update.fromDBObject(BasicDBObject.parse(JSON.serialize(overOrderBill)));
            mongoTemplate.upsert(query, update, OverOrderBill.class);
        }
    }


}
