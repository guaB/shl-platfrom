package com.shl.oauth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: songhonglei
 * @date: 2019-11-21
 */
@RestController
@Slf4j
public class TestController {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

}
