package com.sosd.cloud;

import com.sosd.cloud.service.DemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CloudApplicationTests {
    @DubboReference
    DemoService demoService;

    @Test
    void contextLoads() {
        demoService.hello(":1");
    }

}
