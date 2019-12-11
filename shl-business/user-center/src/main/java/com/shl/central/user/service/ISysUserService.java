package com.shl.central.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shl.common.model.LoginAppUser;
import com.shl.common.model.SysUser;

/**
 * @description:
 * @author: songhonglei
 * @date: 2019-12-11
 */
public interface ISysUserService extends IService<SysUser> {
    /**
     * @param sysUser
     * @return
     */
    LoginAppUser getLoginAppUser(SysUser sysUser);

    /**
     * @param username
     * @return
     */
    LoginAppUser findByUsername(String username);

    /**
     * @param mobile
     * @return
     */
    LoginAppUser findByMobile(String mobile);

    /**
     * @param openId
     * @return
     */
    LoginAppUser findByOpenId(String openId);
}
