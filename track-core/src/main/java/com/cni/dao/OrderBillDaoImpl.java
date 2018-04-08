package com.cni.dao;

import com.alibaba.fastjson.JSON;
import com.cni.dao.entity.OrderBill;
import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class OrderBillDaoImpl implements OrderBillDao {

    @Value("${pageContentSize:1000}")
    private int pageContentSize = 1000;
    @Value("${accomplishOver:30}")
    private int accomplishOver = 30;
    @Value("${longTimeNoUpdateOver:5}")
    private int longTimeNoUpdateOver = 5;
    private final MongoTemplate mongoTemplate;
    private static final Object[] ACCOMPLISH_STATE = new String[]{"Lost", "Delivered", "Returned Delivered"};


    @Autowired
    public OrderBillDaoImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void upsert(OrderBill orderBill) {
        Query query = new Query(Criteria.where("_id").is(orderBill.getNumber()));
        Update update = Update.fromDBObject(BasicDBObject.parse(JSON.toJSONString(orderBill)));
        mongoTemplate.upsert(query, update, OrderBill.class);
    }

    /**
     * 获取所有单号
     *
     * @return 单号
     */
    @Override
    public List<String> findAllNumber() {
        BasicDBObject fieldsObject = new BasicDBObject();
        fieldsObject.append("_id", true);
        Query query = new BasicQuery(new BasicDBObject(), fieldsObject);
        List<OrderBill> all = mongoTemplate.find(query, OrderBill.class);
        return all.stream()
                .map(OrderBill::getNumber)
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * 获取只包含单号和尾程公司的运单实体类
     *
     * @return
     */
    @Override
    public List<OrderBill> findAllNumberAndTailCompany() {
        BasicDBObject fieldsObject = new BasicDBObject();
        fieldsObject.append("_id", true);
        fieldsObject.append("tailCompany", true);
        Query query = new BasicQuery(new BasicDBObject(), fieldsObject);
        return mongoTemplate.find(query, OrderBill.class);
    }

    @Override
    public List<OrderBill> findFirstScnasDateByNumbers(List<String> nums) {
        BasicDBObject fieldsObject = new BasicDBObject();
        fieldsObject.put("scans.date", true);
        BasicQuery basicQuery = new BasicQuery(new BasicDBObject(), fieldsObject);
        basicQuery.addCriteria(Criteria.where("_id").in(nums));
        return mongoTemplate.find(basicQuery, OrderBill.class);
    }

    @Override
    public List<OrderBill> findOverOrderBill() {
        LocalDateTime deadLine = LocalDateTime.now().plusDays(-accomplishOver);
        Long longTimeStamp = deadLine.atZone(ZoneId.systemDefault()) //accomplishOver天数之前的时间戳
                .toInstant()
                .toEpochMilli();

        Criteria criteria = Criteria.where("scans.0.status").in(ACCOMPLISH_STATE)
                .and("scans.0.date").lte(longTimeStamp);

        Query query = new Query(criteria);
        long count = mongoTemplate.count(query, OrderBill.class);
        System.out.println("查询到总数：" + count);

        //分页计算
        long page = count / pageContentSize;
        page = count % pageContentSize > 0 ? page + 1 : page;

        List<OrderBill> orderBills = new ArrayList<>();
        for (int i = 0; i < page; i++) {
            query.skip(i * pageContentSize).limit(pageContentSize);
            List<OrderBill> pageContent = mongoTemplate.find(query, OrderBill.class);
            System.out.println("第" + i + "页");
            orderBills.addAll(pageContent);
        }
        return orderBills;
    }

    @Override
    public void removeOrderBill(List<String> orderNums) {
        Query query=new Query(Criteria.where("_id").in(orderNums));
        mongoTemplate.remove(query,OrderBill.class);
    }

    @Override
    public List<String> findExpirdOrderBill() {
        LocalDateTime deadLine = LocalDateTime.now().plusDays(-longTimeNoUpdateOver);
        Long longTimeStamp = deadLine.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        BasicDBObject fieldsObject = new BasicDBObject();
        fieldsObject.put("_id", true);
        //只返回指定字段
        BasicQuery basicQuery = new BasicQuery(new BasicDBObject(), fieldsObject);

        basicQuery.addCriteria(Criteria.where("scans.0.status").nin(ACCOMPLISH_STATE)
                .and("scans.0.date").lte(longTimeStamp));

        List<OrderBill> orderBills = mongoTemplate.find(basicQuery, OrderBill.class);
        return orderBills.stream().map(OrderBill::getNumber).collect(Collectors.toList());
    }
}
