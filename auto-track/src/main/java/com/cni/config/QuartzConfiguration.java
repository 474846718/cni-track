package com.cni.config;

import com.cni.dao.OrderBillDao;

import com.cni.dao.repository.OverOrderBillDao;
import com.cni.httptrack.OrderTracker;
import com.cni.job.OrderBillJobs;
import com.cni.matcher.Matchers;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;


@Configuration
public class QuartzConfiguration implements ApplicationContextAware {

    private OrderBillDao orderBillDao;
    private OverOrderBillDao overOrderBillDao;
    private OrderTracker orderTracker;
    private ApplicationContext applicationContext;

    @Autowired
    public QuartzConfiguration(OrderBillDao orderBillDao,
                               OverOrderBillDao overOrderBillDao,
                               OrderTracker orderTracker) {
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
        return orderBillJobs;
    }

    @Bean("checkExpiredOrders")
    public MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean() {
        MethodInvokingJobDetailFactoryBean factoryBean = new MethodInvokingJobDetailFactoryBean();
        factoryBean.setBeanFactory(applicationContext);
        factoryBean.setTargetMethod("checkExpiredOrders");
        factoryBean.setTargetBeanName("orderBillJobs");
        return factoryBean;
    }

    @Bean("autoTrackOrders")
    public MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean1() {
        MethodInvokingJobDetailFactoryBean factoryBean = new MethodInvokingJobDetailFactoryBean();
        factoryBean.setBeanFactory(applicationContext);
        factoryBean.setTargetBeanName("orderBillJobs");
        factoryBean.setTargetMethod("autoTrackOrders");
        return factoryBean;
    }

    @Bean("restoreOverOrders")
    public MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean2() {
        MethodInvokingJobDetailFactoryBean factoryBean = new MethodInvokingJobDetailFactoryBean();
        factoryBean.setBeanFactory(applicationContext);
        factoryBean.setTargetBeanName("orderBillJobs");
        factoryBean.setTargetMethod("restoreOverOrders");
        return factoryBean;
    }


    @Bean
    public SimpleTriggerFactoryBean simpleTriggerFactoryBean1(JobDetail restoreOverOrders) {
        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        factoryBean.setJobDetail(restoreOverOrders);
        factoryBean.setRepeatInterval(1000L * 60 * 60 * 24); //毫秒
        factoryBean.setPriority(100);
        return factoryBean;
    }

    @Bean
    public SimpleTriggerFactoryBean simpleTriggerFactoryBean(JobDetail checkExpiredOrders) {
        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        factoryBean.setJobDetail(checkExpiredOrders);
        factoryBean.setRepeatInterval(1000L * 60 * 60 * 24); //毫秒
        factoryBean.setPriority(50);
        return factoryBean;
    }


    @Bean
    public SimpleTriggerFactoryBean simpleTriggerFactoryBean2(JobDetail autoTrackOrders) {
        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        factoryBean.setJobDetail(autoTrackOrders);
        factoryBean.setRepeatInterval(1000L * 60 * 60); //毫秒
        factoryBean.setPriority(1);
        factoryBean.setStartDelay(1000L * 60 * 5); //五分钟后开始执行
        return factoryBean;
    }


    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(Trigger... triggers) {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setAutoStartup(true);
        schedulerFactoryBean.setTriggers(triggers);
        return schedulerFactoryBean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}