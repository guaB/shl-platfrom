package com.shl.security.service.impl;
import	java.awt.Desktop.Action;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shl.security.mapper.UserMapper;
import com.shl.security.model.User;
import com.shl.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: songhonglei
 * @date: 2019-11-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public List<User> getUsers(Integer userClass) {
        return userMapper.getUsers(userClass);
    }
}
