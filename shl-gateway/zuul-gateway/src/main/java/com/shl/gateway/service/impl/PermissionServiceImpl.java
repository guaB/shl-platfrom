package com.shl.gateway.service.impl;

import com.shl.common.model.SysMenu;
import com.shl.oauth2.service.impl.DefaultPermissionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author songhonglei
 * @version 1.0
 * @description 请求权限判断service
 * @date 2019/12/8
 */
@Slf4j
@Service
public class PermissionServiceImpl extends DefaultPermissionServiceImpl {


    @Override
    public List<SysMenu> findMenuByRoleCodes(String roleCodes) {
        return null;
    }

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        return hasPermission(authentication, request.getMethod(), request.getRequestURI());
    }
}
