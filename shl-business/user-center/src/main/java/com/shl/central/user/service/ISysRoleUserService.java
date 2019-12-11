package com.shl.central.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shl.central.user.model.SysRoleUser;
import com.shl.common.model.SysRole;

import java.util.List;

/**
 * @author songhonglei
 * @version 1.0
 * @description
 * @date 2019/12/11
 */
public interface ISysRoleUserService extends IService<SysRoleUser> {

    /**
     * 根据用户Id获取角色
     * @param userId
     * @return
     */
    List<SysRole> findRolesByUserId(Long userId);

}
