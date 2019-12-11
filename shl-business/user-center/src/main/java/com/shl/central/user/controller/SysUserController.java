package com.shl.central.user.controller;

import com.shl.central.user.service.ISysUserService;
import com.shl.common.annotation.LoginUser;
import com.shl.common.model.LoginAppUser;
import com.shl.common.model.Result;
import com.shl.common.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: songhonglei
 * @date: 2019-12-11
 */
@RestController
public class SysUserController {

    @Autowired
    private ISysUserService appUserService;


    @GetMapping("/users/current")
    public Result<LoginAppUser> getLoginAppUser(@LoginUser(isFull = true) SysUser user) {
        return Result.succeed(appUserService.getLoginAppUser(user));
    }


    /**
     * 查询用户登录对象LoginAppUser
     */
    @GetMapping(value = "/users-anon/login", params = "username")
    public LoginAppUser findByUsername(String username) {
        return appUserService.findByUsername(username);
    }

    /**
     * 通过手机号查询用户、角色信息
     *
     * @param mobile 手机号
     */
    @GetMapping(value = "/users-anon/mobile", params = "mobile")
    public LoginAppUser findByMobile(String mobile) {
        return appUserService.findByMobile(mobile);
    }

    /**
     * 根据OpenId查询用户信息
     *
     * @param openId openId
     */
    @GetMapping(value = "/users-anon/openId", params = "openId")
    public LoginAppUser findByOpenId(String openId) {
        return appUserService.findByOpenId(openId);
    }


}
