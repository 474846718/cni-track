package com.cni.config;

import com.cni.dao.OrderBillDao;
import com.cni.dao.OrderBillDaoImpl;
import com.cni.dao.StateMappingDao;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

/**
 * 数据源配置
 */
@Configuration
public class DataSourceConfiguration {
    @Value("${spring.data.mongodb.host:localhost}")
    private String mongodbHost;
    @Value("${spring.data.mongodb.port:27017}")
    private int mongoPort;
    @Value("${spring.data.mongodb.database:}")
    private String mongodbDataBase;
    @Value("${spring.data.mongodb.username:}")
    private String mongodbUser;
    @Value("${spring.data.mongodb.password:}")
    private String mongodbPassword;


    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // 使用Jackson2JsonRedisSerialize 替换默认序列化
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // 设置value的序列化规则和 key的序列化规则
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        ServerAddress serverAddress = new ServerAddress(this.mongodbHost, this.mongoPort);

        MongoClientOptions mongoClientOptions = MongoClientOptions.builder()
//                .connectTimeout(1000 * 30)
//                .maxWaitTime(1000 * 30)
                .description("配置描述")
                .connectionsPerHost(100)
                .minConnectionsPerHost(30)
                .build();

        MongoClient mongoClient;
        if (StringUtils.isEmpty(mongodbUser) && StringUtils.isEmpty(mongodbPassword))
            mongoClient=new MongoClient(serverAddress, mongoClientOptions);
        else {
            List<MongoCredential> mongoCredentials = Collections.singletonList(MongoCredential.createCredential(mongodbUser, mongodbDataBase, mongodbPassword.toCharArray()));
            mongoClient=new MongoClient(serverAddress,mongoCredentials,mongoClientOptions);
        }
        return new MongoTemplate(mongoClient,mongodbDataBase);
    }
}
