package com.shl.central.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shl.central.user.mapper.SysRoleMenuMapper;
import com.shl.central.user.model.SysRoleMenu;
import com.shl.central.user.service.ISysRoleMenuService;
import com.shl.common.model.SysMenu;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @author songhonglei
 * @version 1.0
 * @description
 * @date 2019/12/12
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {

    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;


    @Override
    public List<SysMenu> findMenusByRoleCodes(Set<String> roleCodes, Integer type) {
        return sysRoleMenuMapper.findMenusByRoleCodes(roleCodes, type);
    }
}
