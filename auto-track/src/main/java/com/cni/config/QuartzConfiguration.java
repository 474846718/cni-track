package com.cni.config;

import com.cni.dao.OrderBillDao;
import com.cni.dao.repository.OverOrderBillRepository;
import com.cni.httptrack.OrderTracker;
import com.cni.job.OrderBillJobs;
import com.cni.matcher.Matchers;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;


@Configuration
public class QuartzConfiguration implements ApplicationContextAware {

    private OrderBillDao orderBillDao;
    private OverOrderBillRepository overOrderBillDao;
    private OrderTracker orderTracker;
    private Matchers matchers;
    private ApplicationContext applicationContext;

    @Autowired
    public QuartzConfiguration(OrderBillDao orderBillDao,
                               OverOrderBillRepository overOrderBillDao,
                               OrderTracker orderTracker,
                               Matchers matchers) {
        this.matchers = matchers;
        this.orderTracker = orderTracker;
        this.orderBillDao = orderBillDao;
        this.overOrderBillDao = overOrderBillDao;

    }

    @Description("提供给")
    @Bean("orderBillJobs")
    public OrderBillJobs orderBillJobs() {
        OrderBillJobs orderBillJobs = new OrderBillJobs();
        orderBillJobs.setOrderBillDao(orderBillDao);
        orderBillJobs.setOverOrderBillDao(overOrderBillDao);
        orderBillJobs.setOrderTracker(orderTracker);
        orderBillJobs.setMatchers(matchers);
        return orderBillJobs;
    }

//    @Bean("checkExpiredOrders")
//    public MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean() {
//        MethodInvokingJobDetailFactoryBean factoryBean = new MethodInvokingJobDetailFactoryBean();
//        factoryBean.setBeanFactory(applicationContext);
//        factoryBean.setTargetMethod("checkExpiredOrders");
//        factoryBean.setTargetBeanName("orderBillJobs");
//        return factoryBean;
//    }

//    @Bean("autoTrackOrders")
//    public MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean1() {
//        MethodInvokingJobDetailFactoryBean factoryBean = new MethodInvokingJobDetailFactoryBean();
//        factoryBean.setBeanFactory(applicationContext);
//        factoryBean.setTargetBeanName("orderBillJobs");
//        factoryBean.setTargetMethod("autoTrackOrders");
//        return factoryBean;
//    }

//    @Bean("restoreOverOrders")
//    public MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean2() {
//        MethodInvokingJobDetailFactoryBean factoryBean = new MethodInvokingJobDetailFactoryBean();
//        factoryBean.setBeanFactory(applicationContext);
//        factoryBean.setTargetBeanName("orderBillJobs");
//        factoryBean.setTargetMethod("restoreOverOrders");
//        return factoryBean;
//    }

//    @Bean
//    public SimpleTriggerFactoryBean simpleTriggerFactoryBean(JobDetail checkExpiredOrders) {
//        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
//        factoryBean.setJobDetail(checkExpiredOrders);
//        factoryBean.setRepeatInterval(1000L * 60 * 60 * 24); //毫秒
//        return factoryBean;
//    }

//    @Bean
//    public SimpleTriggerFactoryBean simpleTriggerFactoryBean1(JobDetail restoreOverOrders) {
//        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
//        factoryBean.setJobDetail(restoreOverOrders);
//        factoryBean.setRepeatInterval(1000L * 60 * 60 * 24); //毫秒a
//        return factoryBean;
//    }

//    @Bean
//    public SimpleTriggerFactoryBean simpleTriggerFactoryBean2(JobDetail autoTrackOrders) {
//        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
//        factoryBean.setJobDetail(autoTrackOrders);
//        factoryBean.setRepeatInterval(1000L * 60 * 60); //毫秒
//        return factoryBean;
//    }


//    @Bean
//    public SchedulerFactoryBean schedulerFactoryBean(Trigger... triggers) {
//        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
//        schedulerFactoryBean.setAutoStartup(true);
//        schedulerFactoryBean.setTriggers(triggers);
//        return schedulerFactoryBean;
//    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}