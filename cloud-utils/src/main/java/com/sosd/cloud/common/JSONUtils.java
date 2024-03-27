package com.sosd.cloud.common;

import com.alibaba.fastjson2.JSON;

import java.util.List;

public class JSONUtils {
  public static <T> List<T> jsonToList(String json, Class<T> clazz) {
    return JSON.parseArray(json, clazz);
  }

  public static <T> String listToJson(List<T> list) {
    return JSON.toJSONString(list);
  }
}
