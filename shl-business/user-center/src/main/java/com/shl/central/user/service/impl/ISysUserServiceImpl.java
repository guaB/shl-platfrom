package com.shl.central.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shl.central.user.mapper.SysUserMapper;
import com.shl.central.user.service.ISysUserService;
import com.shl.common.model.LoginAppUser;
import com.shl.common.model.SysUser;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @description:
 * @author: songhonglei
 * @date: 2019-12-11
 */
@Service
public class ISysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Resource
    SysUserMapper userMapper;

    @Override
    public LoginAppUser getLoginAppUser(SysUser sysUser) {
        if(Objects.nonNull(sysUser)){
            LoginAppUser loginAppUser = new LoginAppUser();
            BeanUtils.copyProperties(sysUser, loginAppUser);

        }
        return null;
    }

    @Override
    public LoginAppUser findByUsername(String username) {
        List<SysUser> sysUsers = baseMapper.selectList(new QueryWrapper<SysUser>().eq("username", username));
        return this.getLoginAppUser(getUser(sysUsers));
    }


    @Override
    public LoginAppUser findByMobile(String mobile) {
        List<SysUser> sysUsers = baseMapper.selectList(new QueryWrapper<SysUser>().eq("mobile", mobile));
        return this.getLoginAppUser(getUser(sysUsers));
    }

    @Override
    public LoginAppUser findByOpenId(String openId) {
        List<SysUser> sysUsers = baseMapper.selectList(new QueryWrapper<SysUser>().eq("open_id", openId));
        return this.getLoginAppUser(getUser(sysUsers));
    }

    private SysUser getUser(List<SysUser> sysUsers) {
        SysUser user = null;
        if(CollectionUtils.isNotEmpty(sysUsers)){
            user = sysUsers.get(0);
        }
        return user;
    }
}
