package com.shl.central.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shl.central.user.mapper.SysRoleMenuMapper;
import com.shl.central.user.mapper.SysUserMapper;
import com.shl.central.user.mapper.SysRoleUserMapper;
import com.shl.central.user.service.ISysUserService;
import com.shl.common.constant.CommonConstant;
import com.shl.common.model.LoginAppUser;
import com.shl.common.model.SuperEntity;
import com.shl.common.model.SysMenu;
import com.shl.common.model.SysRole;
import com.shl.common.model.SysUser;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: songhonglei
 * @date: 2019-12-11
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Resource
    SysRoleUserMapper roleUserMapper;

    @Resource
    SysRoleMenuMapper roleMenuMapper;

    @Override
    public LoginAppUser getLoginAppUser(SysUser sysUser) {
        if(Objects.nonNull(sysUser)){
            LoginAppUser loginAppUser = new LoginAppUser();
            BeanUtils.copyProperties(sysUser, loginAppUser);
            List<SysRole> sysRoles = roleUserMapper.findRolesByUserId(sysUser.getId());
            //设置 角色
            loginAppUser.setRoles(sysRoles);
            if(CollectionUtils.isNotEmpty(sysRoles)){
                Set<Long> roleIds = sysRoles.parallelStream().map(SuperEntity::getId).collect(Collectors.toSet());

                List<SysMenu> menus = roleMenuMapper.findMenuByRoleIds(roleIds, CommonConstant.PERMISSION);
                if (CollectionUtils.isNotEmpty(menus)){
                    Set<String> permissions = menus.parallelStream().map(SysMenu::getPath).collect(Collectors.toSet());
                    //设置权限集合
                    loginAppUser.setPermissions(permissions);
                }
            }
            return loginAppUser;
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
