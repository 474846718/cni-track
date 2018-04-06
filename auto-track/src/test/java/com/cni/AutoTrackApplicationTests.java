package com.cni;

import com.cni.dao.OrderBillDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AutoTrackApplicationTests {

	@Autowired
	private OrderBillDao orderBillDao;

	@Test
    @Query
	public void contextLoads() {
	}

}
