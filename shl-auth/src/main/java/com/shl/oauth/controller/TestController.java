package com.shl.oauth.controller;

import com.shl.oauth.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") Integer id){
        return  (User)redisTemplate.opsForValue().get(Integer.toString(id));
    }

    @PostMapping("/users")
    public void postUser(@RequestBody User user){
        redisTemplate.opsForValue().set(user.getId().toString(), user);
    }
}
