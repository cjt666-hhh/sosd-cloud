package com.sosd.cloud.service.impl;

import com.sosd.cloud.service.DemoService;

public class DemoServiceImpl implements DemoService {
  @Override
  public String hello(String name) {
    return String.format("hello %s", name);
  }
}
