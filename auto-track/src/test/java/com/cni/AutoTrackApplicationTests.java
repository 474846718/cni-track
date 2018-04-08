package com.cni;

import com.cni.dao.OrderBillDao;
import com.cni.httptrack.OrderTracker;
import com.cni.job.OrderBillJobs;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)

@SpringBootTest
public class AutoTrackApplicationTests {

    @Autowired
    private OrderBillDao orderBillDao;

    @Autowired
    private OrderTracker orderTracker;

    @Autowired
    private OrderBillJobs orderBillJobs;

    @Test
    public void contextLoads() throws InterruptedException {
        orderBillJobs.restoreOverOrders();
        orderBillJobs.checkExpiredOrders();
        orderBillJobs.autoTrackOrders();
        Thread.sleep(1000000);
    }

    @Test
    public void test() {
        List<String> list = new ArrayList<>();
        list.add("EQ949068728IN");
        orderTracker.startTrack(list);
    }

}
