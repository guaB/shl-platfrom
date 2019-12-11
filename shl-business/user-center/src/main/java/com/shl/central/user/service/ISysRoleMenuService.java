package com.shl.central.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shl.central.user.model.SysRoleMenu;
import com.shl.common.model.SysMenu;

import java.util.List;
import java.util.Set;

/**
 * @author songhonglei
 * @version 1.0
 * @description
 * @date 2019/12/12
 */
public interface ISysRoleMenuService extends IService<SysRoleMenu> {

    List<SysMenu> findMenusByRoleCodes(Set<String> roleCodes, Integer type);
}
