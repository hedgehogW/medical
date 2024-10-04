package com.example.usermanagement.controller;

import com.example.usermanagement.config.RedisConfig;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redistest")
public class RedisTestController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @RequestMapping("/test0")
    public String TestRedis() {

        redisTemplate.opsForValue().set("name1","ymh");
        String ret = (String) redisTemplate.opsForValue().get("name");
        return ret;
    }
}
