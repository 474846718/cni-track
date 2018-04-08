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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;


/**
 * 配置框架
 * 新政渠道只要添加到Matchers类就可以了
 */

@Configuration
public class DomainConfiguration {

    @Value("${transformHost:www.cnilink.com}")
    private String host;

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
    public NeomanConfigHolder neomanHolder() {
        return new NeomanConfigHolder(okHttpClient());
    }

    @Bean
    public Matchers matchers() {
        TrackChannel delhivery = new TrackChannel();
        delhivery.setConverter(new DelhiveryConverter(neomanHolder()));
        delhivery.setOrderNumMatcher(new OrderNumMatcher("1\\d{12}"));
        delhivery.setTag("DELHIVERY");
        delhivery.setUrlSegment("delhivery");

        TrackChannel bluedart = new TrackChannel();
        bluedart.setConverter(new BluedartConverter(neomanHolder()));
        bluedart.setOrderNumMatcher(new OrderNumMatcher("\\d{11}"));
        bluedart.setTag("BLUE DART");
        bluedart.setUrlSegment("bluedart");

        TrackChannel ecom = new TrackChannel();
        ecom.setConverter(new EcomConverter(neomanHolder()));
        ecom.setOrderNumMatcher(new OrderNumMatcher("2\\d{8}"));
        ecom.setTag("ECOM EXPRESS");
        ecom.setUrlSegment("ecom");

        TrackChannel indiapost = new TrackChannel();
        indiapost.setConverter(new IndiapostConverter(neomanHolder()));
        indiapost.setOrderNumMatcher(new OrderNumMatcher("\\w{2}\\d{9}\\w{2}"));
        indiapost.setTag("INDIA POST");
        indiapost.setUrlSegment("indiapost");

        TrackChannel gati = new TrackChannel();
        gati.setConverter(new GatiConverter(neomanHolder()));
        gati.setOrderNumMatcher(new OrderNumMatcher("6\\d{8}"));
        gati.setTag("GATI");
        gati.setUrlSegment("gati");

        Matchers matchers = new Matchers();
        matchers.add(delhivery);
        matchers.add(bluedart);
        matchers.add(ecom);
        matchers.add(indiapost);
        matchers.add(gati);

        return matchers;
    }

    @Bean
    public OrderTracker orderTracker(OrderBillDao orderBillDao) {
        OrderTracker orderTracker = new OrderTracker();
        orderTracker.setSelfDispatchNumHolder(selfDispatchNumHolder());//TODO 以后可以删除
        orderTracker.setScheme("http");
        orderTracker.setHost(host);
        orderTracker.setPort(9999);
        orderTracker.setVersion("v1.0.0");
        orderTracker.setClient(okHttpClient());
        orderTracker.setOrderBillDao(orderBillDao);
        orderTracker.setMatchers(matchers());
        return orderTracker;
    }
}
