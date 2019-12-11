package com.shl.central.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shl.common.constant.CommonConstant;
import com.shl.common.model.SysMenu;

import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: songhonglei
 * @date: 2019-12-11
 */
public interface ISysMenuService extends IService<SysMenu> {

    /**
     * 角色菜单列表
     * @param roleCodes
     * @return
     */
    List<SysMenu>   findByRoleCodes(Set<String> roleCodes , Integer type);
}
