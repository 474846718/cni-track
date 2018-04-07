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
        TrackChannel trackChannel =new TrackChannel();
        trackChannel.setConverter(new DelhiveryConverter(neomanHolder()));
        trackChannel.setOrderNumMatcher(new OrderNumMatcher("1\\d{12}"));
        trackChannel.setTag("DELHIVERY");
        trackChannel.setUrlSegment("delhivery");
        return trackChannel;
    }

    @Bean
    public TrackChannel trackChanne1() {
        TrackChannel trackChannel =new TrackChannel();
        trackChannel.setConverter(new BluedartConverter(neomanHolder()));
        trackChannel.setOrderNumMatcher(new OrderNumMatcher("\\d{11}"));
        trackChannel.setTag("BLUE DART");
        trackChannel.setUrlSegment("bluedart");
        return trackChannel;
    }

    @Bean
    public TrackChannel trackChanne2() {
        TrackChannel trackChannel =new TrackChannel();
        trackChannel.setConverter(new EcomConverter(neomanHolder()));
        trackChannel.setOrderNumMatcher(new OrderNumMatcher("2\\d{8}"));
        trackChannel.setTag("ECOM EXPRESS");
        trackChannel.setUrlSegment("ecom");
        return trackChannel;
    }

    @Bean
    public TrackChannel trackChanne3() {
        TrackChannel trackChannel =new TrackChannel();
        trackChannel.setConverter(new IndiapostConverter(neomanHolder()));
        trackChannel.setOrderNumMatcher(new OrderNumMatcher("\\w{2}\\d{9}\\w{2}"));
        trackChannel.setTag("INDIA POST");
        trackChannel.setUrlSegment("indiapost");
        return trackChannel;
    }

    @Bean
    public TrackChannel trackChanne4() {
        TrackChannel trackChannel =new TrackChannel();
        trackChannel.setConverter(new GatiConverter(neomanHolder()));
        trackChannel.setOrderNumMatcher(new OrderNumMatcher("6\\d{8}"));
        trackChannel.setTag("GATI");
        trackChannel.setUrlSegment("gati");
        return trackChannel;
    }

    @Bean
    public Matchers matchers(TrackChannel... trackChannels) {
        Matchers matchers = new Matchers();
        matchers.add(trackChannels);
        return matchers;
    }
}
