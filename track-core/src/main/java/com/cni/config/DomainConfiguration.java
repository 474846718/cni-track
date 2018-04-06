package com.cni.config;

import com.cni.converter.*;
import com.cni.dao.OrderBillDao;
import com.cni.httptrack.OrderTracker;
import com.cni.httptrack.SelfDispatchNumHolder;
import com.cni.httptrack.TrackChannel;
import com.cni.httptrack.support.MemoryCookieJar;
import com.cni.matcher.Matchers;
import com.cni.matcher.OrderNumMatcher;
import com.cni.statemap.neoman.NeomanConfigHolder;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;


/**
 * 配置框架
 * 新政渠道只要添加到Matchers类就可以了
 */

@Configuration
public class DomainConfiguration {

    @Bean
    public OkHttpClient okHttpClient() {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequestsPerHost(1000);  //设置最大并发请求数
        dispatcher.setMaxRequests(3000);
        return new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .cookieJar(new MemoryCookieJar()) // 需要在Cookie中保存JSessionID
                .dispatcher(dispatcher)
                .retryOnConnectionFailure(true)
                .build();
    }

    @Bean
    public SelfDispatchNumHolder selfDispatchNumHolder() {
        return new SelfDispatchNumHolder();
    }

    @Bean
    public OrderTracker orderTracker(OrderBillDao orderBillDao, RedisTemplate<String, Object> redisTemplate) {
        OrderTracker orderTracker = new OrderTracker();
        orderTracker.setSelfDispatchNumHolder(selfDispatchNumHolder());//TODO 以后可以删除
        orderTracker.setScheme("http");
        orderTracker.setHost("www.cnilink.com");
        orderTracker.setPort(9999);
        orderTracker.setVersion("v1.0.0");
        orderTracker.setClient(okHttpClient());
        orderTracker.setOrderBillDao(orderBillDao);
        orderTracker.setRedisTemplate(redisTemplate);
        orderTracker.setMatchers(matchers());
        return orderTracker;
    }

    @Bean
    public NeomanConfigHolder neomanHolder() {
        return new NeomanConfigHolder(okHttpClient());
    }

    @Bean
    public TrackChannel trackChanne() {
        return new TrackChannel(new DelhiveryConverter(neomanHolder()), "delhivery", new OrderNumMatcher("1\\d{12}"));
    }

    @Bean
    public TrackChannel trackChanne1() {
        return new TrackChannel(new BluedartConverter(neomanHolder()), "bluedart", new OrderNumMatcher("\\d{11}"));
    }

    @Bean
    public TrackChannel trackChanne2() {
        return new TrackChannel(new EcomConverter(neomanHolder()), "ecom", new OrderNumMatcher("2\\d{8}"));
    }

    @Bean
    public TrackChannel trackChanne3() {
        return new TrackChannel(new IndiapostConverter(neomanHolder()), "indiapost", new OrderNumMatcher("\\w{2}\\d{9}\\w{2}"));
    }

    @Bean
    public TrackChannel trackChanne4() {
        return new TrackChannel(new GatiConverter(neomanHolder()), "gati", new OrderNumMatcher("6\\d{8}"));
    }

    @Bean
    public Matchers matchers(TrackChannel... trackChannels) {
        Matchers matchers = new Matchers();
        matchers.add(trackChannels);
        return matchers;
    }
}
