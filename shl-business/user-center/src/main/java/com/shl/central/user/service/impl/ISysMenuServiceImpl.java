package com.shl.central.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shl.central.user.mapper.SysMenuMapper;
import com.shl.central.user.service.ISysMenuService;
import com.shl.common.model.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: songhonglei
 * @date: 2019-12-11
 */
@Service
public class ISysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>  implements ISysMenuService {

    @Resource
    SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> findByRoleCodes(Set<String> roleCodes, Integer type) {
        return sysMenuMapper.findByRoleCodes(roleCodes, type);
    }
}
