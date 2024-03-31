package com.sosd.cloud.controller.demo;

import cn.hutool.core.date.DateUtil;
import com.sosd.cloud.service.DemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sentinel")
public class SentinelDemo {
    @DubboReference
    DemoService demoService;

    @RequestMapping("/1")
    public String sentinelTest() {
        demoService.hello("花火");
        return "接口调用成功" + DateUtil.now();
    }
}
