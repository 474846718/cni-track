package com.cni;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootConfiguration
@SpringBootTest
public class TrackCoreApplicationTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrackCoreApplicationTests.class);

    @Test
    public void contextLoads() {
        StringHttpMessageConverter stringHttpMessageConverter;
    }

    @Test
    public void ref() {
    }
}
