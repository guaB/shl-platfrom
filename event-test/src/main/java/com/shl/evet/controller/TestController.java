package com.shl.evet.controller;

import com.shl.evet.module.User;
import com.shl.evet.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author songhonglei
 * @version 1.0
 * @description
 * @date 2019/11/30
 */
@RestController
public class TestController {

    @Autowired
    UserRegisterService userRegisterService;

    @PostMapping("/user/register")
    public Object userRegister(@RequestBody User user) throws InterruptedException {
        Map<String, Object> map = new HashMap<>(16);
        userRegisterService.register(user);
        map.put("code", 200);
        map.put("msg", "注册成功");
        return map;
    }
}
