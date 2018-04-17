package com.cni.dao;

import com.alibaba.fastjson.JSON;
import com.cni.dao.entity.ArchiveWaybill;
import com.mongodb.BasicDBObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ArchiveWaybillDaoImpl implements ArchiveWaybillDao {

    private static final Logger LOGGER= LoggerFactory.getLogger(ArchiveWaybillDaoImpl.class);
    private final MongoTemplate mongoTemplate;

    @Autowired
    public ArchiveWaybillDaoImpl(MongoTemplate mongoTemplate) {
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
        for (ArchiveWaybill archiveWaybill: archiveWaybills) {
            long date = archiveWaybill.getSavePoints().get(0).getDate();
            Query query = new Query(Criteria.where("infoNodes.0.date").is(date));
            String s=JSON.toJSONString(archiveWaybill);
            Update update = Update.fromDBObject(BasicDBObject.parse(s));
            try{
                mongoTemplate.upsert(query, update, ArchiveWaybill.class);
            }catch (Exception ignored){
                LOGGER.warn("更新失败");
            }
        }
    }


}
