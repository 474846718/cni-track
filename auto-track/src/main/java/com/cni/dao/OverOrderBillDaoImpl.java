package com.cni.dao;

import com.cni.dao.entity.ArchiveWaybill;
import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

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
     * @param archiveWaybills 查询条件
     * @return 单号
     */
    public void upsertLatestInfoNodeDate(List<ArchiveWaybill> archiveWaybills) {
        for (ArchiveWaybill archiveWaybill : archiveWaybills) {
            long date = archiveWaybill.getInfoNodes().get(0).getDate();
            Query query = new Query(Criteria.where("infoNodes.0.date").is(date));
            Update update = Update.fromDBObject(BasicDBObject.parse(JSON.serialize(archiveWaybill)));
            mongoTemplate.upsert(query, update, ArchiveWaybill.class);
        }
    }


}
