package com.sosd.cloud.controller.demo;

import com.sosd.cloud.common.JSONUtils;
import com.sosd.cloud.entity.pojo.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RedisDemo {
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    public void putListToRedis(List<Demo> list, Integer id) {
        redisTemplate.opsForValue().set("demo-list-key" + id, JSONUtils.listToJson(list));
    }

    public void getListFromRedis(Integer id) {
        String json = Objects.requireNonNull(redisTemplate.opsForValue().get("demo-list-key" + id)).toString();
        List<Demo> list = JSONUtils.jsonToList(json, Demo.class);
        for (Demo demo : list) {
            System.out.println(demo);
        }
    }
}
