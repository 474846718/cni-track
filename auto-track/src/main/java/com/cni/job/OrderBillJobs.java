package com.cni.job;


import com.alibaba.fastjson.JSON;
import com.cni.dao.ArchiveWaybillDao;
import com.cni.dao.CompleteWaybillRepository;
import com.cni.dao.OntrackWaybillRepository;
import com.cni.dao.entity.ArchiveWaybill;
import com.cni.dao.entity.CompleteWaybill;
import com.cni.dao.entity.OntrackWaybill;
import com.cni.dao.entity.Waybill;
import com.cni.httptrack.WaybillTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
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

    private CompleteWaybillRepository completeWaybillRepository;
    private OntrackWaybillRepository ontrackWaybillRepository;
    private ArchiveWaybillDao archiveWaybillDao;
    private WaybillTracker waybillTracker;
    private File file;

    public ArchiveWaybillDao getArchiveWaybillDao() {
        return archiveWaybillDao;
    }

    public void setArchiveWaybillDao(ArchiveWaybillDao archiveWaybillDao) {
        this.archiveWaybillDao = archiveWaybillDao;
    }

    public CompleteWaybillRepository getCompleteWaybillRepository() {
        return completeWaybillRepository;
    }

    public void setCompleteWaybillRepository(CompleteWaybillRepository completeWaybillRepository) {
        this.completeWaybillRepository = completeWaybillRepository;
    }

    public OntrackWaybillRepository getOntrackWaybillRepository() {
        return ontrackWaybillRepository;
    }

    public void setOntrackWaybillRepository(OntrackWaybillRepository ontrackWaybillRepository) {
        this.ontrackWaybillRepository = ontrackWaybillRepository;
    }

    public WaybillTracker getWaybillTracker() {
        return waybillTracker;
    }

    public void setWaybillTracker(WaybillTracker waybillTracker) {
        this.waybillTracker = waybillTracker;
    }

    /**
     * 重新映射运单最新的状态
     * 然后检查活跃表的完结状态的运单
     * 并且转存到归档表
     */
    public synchronized void restoreOverOrders() {
        logger.warn("===开始归档完结表===");
        Instant instant = Instant.now().plus(-1, ChronoUnit.MONTHS);

        //获取完结表记录
        List<CompleteWaybill> completeWaybills = completeWaybillRepository.findByLatestDateLessThan(instant.toEpochMilli());
        logger.warn("记录总数：" + completeWaybills.size());
        //插入归档表记录
        List<ArchiveWaybill> archiveWaybills = completeWaybills.stream()
                .map(completeWaybill -> JSON.parseObject(JSON.toJSONString(completeWaybill), ArchiveWaybill.class))
                .collect(Collectors.toList());
        archiveWaybillDao.upsertLatestInfoNodeDate(archiveWaybills);
        //删除完成表记录
        completeWaybillRepository.delete(completeWaybills);
        logger.warn("===结束归档活跃表===");
    }

    /**
     * 获取长时间无更新运单
     * 并且取消追踪
     */
    public synchronized void checkExpiredOrders() throws IOException {
        logger.warn("===开始检查超时运单===");
        Instant instant = Instant.now().plus(-5, ChronoUnit.DAYS);

        List<OntrackWaybill> expired = ontrackWaybillRepository.findByLatestDateLessThan(instant.toEpochMilli());
        if (Objects.nonNull(file)) {
            String mergeStr = StringUtils.collectionToDelimitedString(expired, "\n");
            StreamUtils.copy(mergeStr, Charset.defaultCharset(), new FileOutputStream(file));
        }
        logger.warn("记录总数：" + expired.size());
        ontrackWaybillRepository.delete(expired);
        logger.warn("===结束检查超时运单===");
    }


    /**
     * 追踪活跃表的运单
     */
    public synchronized void autoTrackOrders() {
        //获取实体对象 分组
        logger.warn("===开始查询活跃表未完成运单===");
        List<OntrackWaybill> ontrackWaybills = ontrackWaybillRepository.findAll();//todo 查找所有非空
        logger.warn("记录总数：" + ontrackWaybills.size());
        List<String> orderNums = ontrackWaybills.stream()
                .map(Waybill::getNumber)
                .collect(Collectors.toList());
        waybillTracker.startTrack(orderNums);
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