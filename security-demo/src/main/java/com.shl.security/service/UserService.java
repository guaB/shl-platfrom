package com.shl.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shl.security.model.User;

import java.util.List;

/**
 * @description:
 * @author: songhonglei
 * @date: 2019-11-20
 */
public interface UserService extends IService<User> {
    List<User> getUsers(Integer userClass);
}
