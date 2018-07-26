package com.shengruitn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class LoggerTest {
    @Test
    public void test() {
        String name = "tangah";
        String pwd = "pwd";

        log.debug("debug...");
        log.error("error...");
        log.info("info...");
        log.info( "name= [{}], pwd = [{}]", name, pwd );

    }
}
