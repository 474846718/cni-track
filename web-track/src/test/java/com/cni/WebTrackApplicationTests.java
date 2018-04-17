package com.cni;

import com.cni.dao.entity.Waybill;
import com.cni.httptrack.WaybillTracker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebTrackApplicationTests {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebTrackApplicationTests.class);

    @Autowired
    private WaybillTracker waybillTracker;

    @Test
    public void contextLoads() {
        List<String> num = new ArrayList<>();

        num.add("1521513493906");
        List<Waybill> waybills = waybillTracker.startTrackRet(num);
        LOGGER.warn("");
    }


}
