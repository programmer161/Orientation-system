package com.Digital_Orientation_System;


import org.junit.Test;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogbackTest {

    private final Logger logger= LoggerFactory.getLogger(LogbackTest.class);

    @Test
    public void LogToSql(){
        logger.info("数据库日志info");
        logger.error("数据库日志error");
    }
}

