package com.cni.config;

import com.cni.dao.OrderBillDao;
import com.cni.dao.StateMappingDao;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@Configuration
@EnableMongoRepositories("com.cni.dao")
@ConditionalOnMissingBean({OrderBillDao.class})
public class DaoConfiguration {}
