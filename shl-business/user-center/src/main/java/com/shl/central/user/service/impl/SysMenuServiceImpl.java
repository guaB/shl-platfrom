package com.shl.central.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shl.central.user.mapper.SysMenuMapper;
import com.shl.central.user.service.ISysMenuService;
import com.shl.central.user.service.ISysRoleMenuService;
import com.shl.common.model.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: songhonglei
 * @date: 2019-12-11
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Autowired
    private ISysRoleMenuService roleMenuService;

    @Override
    public List<SysMenu> findMenusByRoleCodes(Set<String> roleCodes, Integer type) {
        return roleMenuService.findMenusByRoleCodes(roleCodes, type);
    }
}
