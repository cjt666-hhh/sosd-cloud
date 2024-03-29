package com.sosd.cloud.controller.demo;

import cn.hutool.core.date.DateUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sentinel")
public class SentinelDemo {
    @RequestMapping("/1")
    public String sentinelTest() {
        return "接口调用成功" + DateUtil.now();
    }
}
