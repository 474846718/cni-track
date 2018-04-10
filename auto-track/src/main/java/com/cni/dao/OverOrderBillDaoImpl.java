package com.cni.dao;

import com.cni.dao.entity.OrderBill;
import com.cni.dao.entity.OverOrderBill;
import com.mongodb.BasicDBObject;
import org.springframework.beans.annotation.AnnotationBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
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
    public List<String> findByLatestInfoNodeDate(List<OverOrderBill> overOrderBills) {
        List<Long> dates = overOrderBills.stream()
                .map(OverOrderBill::getInfoNodes)
                .filter(CollectionUtils::isEmpty)
                .map(infoNodes -> infoNodes.get(0))
                .filter(Objects::nonNull)
                .map(OverOrderBill.InfoNode::getDate)
                .collect(Collectors.toList());

        BasicDBObject fieldsObject = new BasicDBObject();
        fieldsObject.put("number", true);
        BasicQuery basicQuery = new BasicQuery(new BasicDBObject(), fieldsObject);
        basicQuery.addCriteria(Criteria.where("infoNodes.0.date").in(dates));
        List<OverOrderBill> stored = mongoTemplate.find(basicQuery, OverOrderBill.class);

        return stored.stream()
                .map(OverOrderBill::getNumber)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public void insert(List<OverOrderBill> overOrderBills) {
        mongoTemplate.insertAll(overOrderBills);
    }

}
