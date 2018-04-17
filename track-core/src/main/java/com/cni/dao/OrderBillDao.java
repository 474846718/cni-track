package com.cni.dao;

import com.alibaba.fastjson.JSON;
import com.cni.dao.entity.Waybill;
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
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class OrderBillDao {

    public static final String DELIVERED_COLLECTION = "deliveredOrderBill";
    public static final String ORDERBILL = "orderBill";

    @Value("${pageContentSize:1000}")
    private int pageContentSize = 1000;
    @Value("${accomplishOver:30}")
    private int accomplishOver = 30;
    @Value("${longTimeNoUpdateOver:5}")
    private int longTimeNoUpdateOver = 5;
    private final MongoTemplate mongoTemplate;
    private static final Object[] ACCOMPLISH_STATE = new String[]{"Lost", "Delivered", "Returned Delivered"};


    @Autowired
    public OrderBillDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void upsert(Waybill waybill) {
        Query query = new Query(Criteria.where("_id").is(waybill.getNumber()));
        Update update = Update.fromDBObject(BasicDBObject.parse(JSON.toJSONString(waybill)));
        if (1 <= waybill.getSavePoints().size()) {
            String latestStatus = waybill.getSavePoints().get(0).getStatus();
            String collectionName = "Delivered".equals(latestStatus) ? DELIVERED_COLLECTION : ORDERBILL;
            mongoTemplate.upsert(query, update, Waybill.class, collectionName);
        }
    }

    public List<Waybill> findById(List<String> ids) {
        Criteria criteria = Criteria.where("_id").in(ids);
        Query query = new Query(criteria);
        return mongoTemplate.find(query, Waybill.class);
    }


    /**
     * 查询所有在追踪的单号
     * 包含尾程公司标记
     *
     * @return 实体对象
     */
    public List<Waybill> findAllOnTrack() {
        BasicDBObject fieldsObject = new BasicDBObject();
        fieldsObject.append("_id", true);
        fieldsObject.append("tailCompany", true);
        Query query = new BasicQuery(new BasicDBObject(), fieldsObject);
//        query.addCriteria(Criteria.where("scans.0.status").nin(ACCOMPLISH_STATE));
        return mongoTemplate.find(query, Waybill.class);
    }


    /**
     * 查找所有完结表超过一个月的
     *
     * @return
     */

    public List<Waybill> findOverOrderBill(int page, int pagesize) {
        LocalDateTime deadLine = LocalDateTime.now().plusDays(-accomplishOver);
        Long longTimeStamp = deadLine.atZone(ZoneId.systemDefault()) //accomplishOver天数之前的时间戳
                .toInstant()
                .toEpochMilli();

        Criteria criteria = Criteria.where("scans.0.status").in(ACCOMPLISH_STATE)
                .and("scans.0.date").lte(longTimeStamp);

        Query query = new Query(criteria);
        query.skip(page * pagesize).limit(pagesize);
        return mongoTemplate.find(query, Waybill.class, DELIVERED_COLLECTION);
    }

    /**
     * @param orderNums 删除所有单号
     */
    public void removeOrderBill(List<String> orderNums) {
        Query query = new Query(Criteria.where("_id").in(orderNums));
        mongoTemplate.remove(query, Waybill.class);
        mongoTemplate.remove(query, Waybill.class, DELIVERED_COLLECTION);
    }

    /**
     * 查找超时运单号
     */
    public List<String> findExpirdOrderBill() {
        LocalDateTime deadLine = LocalDateTime.now().plusDays(-longTimeNoUpdateOver);
        Long longTimeStamp = deadLine.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        BasicDBObject fieldsObject = new BasicDBObject();
        fieldsObject.put("_id", true);

        //只返回指定字段
        BasicQuery basicQuery = new BasicQuery(new BasicDBObject(), fieldsObject);
        Criteria criteria = Criteria.where("scans.0.status").
                nin(ACCOMPLISH_STATE).
                and("scans.0.date").
                lte(longTimeStamp);
        basicQuery.addCriteria(criteria);

        List<Waybill> waybills = mongoTemplate.find(basicQuery, Waybill.class);
        return waybills.stream()
                .map(Waybill::getNumber)
                .collect(Collectors.toList());
    }
}
