package com.shl.security.controller;

import com.shl.security.model.ResultWrapper;
import com.shl.security.model.User;
import com.shl.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @author: songhonglei
 * @date: 2019-11-20
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users/{class}")
    public ResultWrapper<List<User>> getUsers(@PathVariable("class") Integer clazz) {
        return ResultWrapper.success(userService.getUsers(clazz));
    }
}
