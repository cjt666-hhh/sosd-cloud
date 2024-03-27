package com.sosd.cloud.service.impl;

import com.sosd.cloud.service.DemoService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class DemoServiceImpl implements DemoService {
    @Override
    public void hello(String name) {

        System.out.println("hello " + name);
    }
}
