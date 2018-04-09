package com.cni.job;


import com.cni.dao.OrderBillDao;
import com.cni.dao.entity.OrderBill;
import com.cni.dao.entity.OverOrderBill;
import com.cni.dao.repository.OverOrderBillDao;
import com.cni.httptrack.OrderTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 任务类
 * 的方法和调度器绑定
 * 这些任务和运单相关
 */

public class OrderBillJobs {
    private static final Logger logger = LoggerFactory.getLogger(OrderBillJobs.class);

    private OrderBillDao orderBillDao;
    private OverOrderBillDao overOrderBillDao;
    private OrderTracker orderTracker;
    private File file;

    public OrderBillDao getOrderBillDao() {
        return orderBillDao;
    }

    public void setOrderBillDao(OrderBillDao orderBillDao) {
        this.orderBillDao = orderBillDao;
    }

    public OverOrderBillDao getOverOrderBillDao() {
        return overOrderBillDao;
    }

    public void setOverOrderBillDao(OverOrderBillDao overOrderBillDao) {
        this.overOrderBillDao = overOrderBillDao;
    }

    public OrderTracker getOrderTracker() {
        return orderTracker;
    }

    public void setOrderTracker(OrderTracker orderTracker) {
        this.orderTracker = orderTracker;
    }

    /**
     * 重新映射运单最新的状态
     * 然后检查活跃表的完结状态的运单
     * 并且转存到归档表
     */
    public synchronized void restoreOverOrders() {
        logger.warn("===开始归档活跃表===");
        List<OrderBill> restoreOrderBills = orderBillDao.findOverOrderBill();
        logger.warn("记录总数：" + restoreOrderBills.size());
        List<OverOrderBill> overOrderBills = restoreOrderBills.stream().map(OverOrderBill::new).collect(Collectors.toList());
        overOrderBillDao.insert(overOrderBills);//todo 要做按最新日期更新
        List<String> restoreNums = restoreOrderBills.stream().map(OrderBill::getNumber).collect(Collectors.toList());
        orderBillDao.removeOrderBill(restoreNums);
        logger.warn("===结束归档活跃表===");
    }

    /**
     * 获取长时间无更新运单
     * 并且取消追踪
     */
    public synchronized void checkExpiredOrders() {
        logger.warn("===开始检查超时运单===");
        List<String> expired = orderBillDao.findExpirdOrderBill();
        logger.warn("记录总数：" + expired.size());

        orderBillDao.removeOrderBill(expired);
        logger.warn("===结束检查超时运单===");
    }


    /**
     * 追踪活跃表的运单
     */
    public synchronized void autoTrackOrders() {
        //获取实体对象 分组
        logger.warn("===开始查询活跃表未完成运单===");
        List<OrderBill> orderBills = orderBillDao.findAllOnTrack();
        logger.warn("记录总数：" + orderBills.size());
        List<String> orderNums = orderBills.stream().map(OrderBill::getNumber).collect(Collectors.toList());
        if (Objects.nonNull(file)) {
            try {
                FileWriter writer = new FileWriter(file, true);
                for (String num : orderNums)
                    writer.write(num);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        orderTracker.startTrack(orderNums);
        logger.warn("===结束查询活跃表未完成运单===");
    }

    @PostConstruct
    public void postConstruct() throws IOException {
        if (!"Windows 10".equals(System.getProperty("os.name"))) {
            file = new File("/root/expiredOrder.txt");
            if (!file.exists())
                Assert.isTrue(file.createNewFile(), "创建文件失败！");
        }
    }
}