package com.cni.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
public class CacheConfiguration extends CachingConfigurerSupport {
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public CacheConfiguration(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Bean("redisCacheManager")
    @Override
    public CacheManager cacheManager() {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        cacheManager.setDefaultExpiration(60L * 10);
        Map<String, Long> expires = new HashMap<>();
        expires.put("orders", 60L * 10);
        cacheManager.setExpires(expires);
        return cacheManager;
    }


}