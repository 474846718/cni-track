package com.cni.dao;

import com.alibaba.fastjson.JSON;
import com.cni.dao.entity.OrderBill;
import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
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

    private int pageContentSize = 1000;
    private int accomplishOver = 30;    //完结状态天数
    private int longTimeNoUpdateOver = 5;  //长时间无更新天数
    private final MongoTemplate mongoTemplate;
    private static final Object[] ACCOMPLISH_STATE = new String[]{"Lost", "Delivered", "Returned Delivered"};


    @Autowired
    public OrderBillDaoImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * 更新或者插入一条运单记录
     *
     * @param orderBill 运单数据
     */
    @Override
    public void upsert(OrderBill orderBill) {
        Query query = new Query(Criteria.where("_id").is(orderBill.getNumber()));
        Update update = Update.fromDBObject(BasicDBObject.parse(JSON.toJSONString(orderBill)));
        mongoTemplate.upsert(query, update, OrderBill.class);
    }

    /**
     * 获取所有运单号
     *
     * @return
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
     * 通过单号获取第一个节点时间
     *
     * @param nums
     * @return
     */
    @Override
    public List<OrderBill> findFirstScansDateByNumbers(List<String> nums) {
        BasicDBObject fieldsObject = new BasicDBObject();
        fieldsObject.put("scans.date", true);
        BasicQuery basicQuery = new BasicQuery(new BasicDBObject(), fieldsObject);
        basicQuery.addCriteria(Criteria.where("_id").in(nums));
        return mongoTemplate.find(basicQuery, OrderBill.class);
    }


    /**
     * 找出所有超过 完结状态的
     *
     * @return
     */
    @Override
    public List<OrderBill> findOverOrderBill() {
        LocalDateTime deadLine = LocalDateTime.now().plusDays(-accomplishOver);
        Long longTimeStamp = deadLine.atZone(ZoneId.systemDefault())
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

        //这里想办法添加原子操作
        List<OrderBill> documents = new ArrayList<>();
        for (int i = 0; i < page; i++) {
            query.skip(i * pageContentSize).limit(pageContentSize);
            List<OrderBill> pageContent = mongoTemplate.findAllAndRemove(query, OrderBill.class);
            System.out.println("第" + i + "页");
            documents.addAll(pageContent);
        }
        return documents;
    }

    /**
     * 删除完结状态的运单
     */
    @Override
    public void removeOverOrderBill(List<String> nums) {
        Query query = new Query();
        mongoTemplate.remove(query, OrderBill.class);
    }


    /**
     * 查找所有长时间无更新单号
     * @return
     */
    @Override
    public List<String> findExpiredOrderBill() {
        LocalDateTime deadLine = LocalDateTime.now().plusDays(-longTimeNoUpdateOver);
        Long longTimeStamp = deadLine.atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli();

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
