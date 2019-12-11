package com.shl.central.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shl.central.user.mapper.SysRoleUserMapper;
import com.shl.central.user.model.SysRoleUser;
import com.shl.central.user.service.ISysRoleUserService;
import com.shl.common.model.SysRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author songhonglei
 * @version 1.0
 * @description
 * @date 2019/12/11
 */
@Service
public class SysRoleUserServiceImpl extends ServiceImpl<SysRoleUserMapper, SysRoleUser> implements ISysRoleUserService {

    @Resource
    private SysRoleUserMapper sysRoleUserMapper;

    @Override
    public List<SysRole> findRolesByUserId(Long userId) {
        return sysRoleUserMapper.findRolesByUserId(userId);
    }

}
