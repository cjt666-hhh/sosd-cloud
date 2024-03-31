package com.sosd.cloud;

import com.sosd.cloud.controller.demo.RedisDemo;
import com.sosd.cloud.entity.pojo.Demo;
import com.sosd.cloud.service.DemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

@SpringBootTest
class CloudApplicationTests {
    @DubboReference
    DemoService demoService;
    @Autowired
    RedisDemo redisDemo;
    @Test
    void dubboTest() {
        System.out.println(demoService.hello("花火"));
    }
    @Test
    void redisTest() {
        List<Demo> demos = Collections.singletonList(new Demo(1, "demo", "这是一个demo"));
        redisDemo.putListToRedis(demos, 1);
        redisDemo.getListFromRedis(1);
    }
}
