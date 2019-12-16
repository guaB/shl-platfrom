package com.shl.gateway.service.impl;

import com.shl.common.model.SysMenu;
import com.shl.gateway.feign.MenuService;
import com.shl.oauth.service.impl.DefaultPermissionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@Service("permissionService")
public class PermissionServiceImpl extends DefaultPermissionServiceImpl {

    @Autowired
    MenuService menuService;

    @Override
    public List<SysMenu> findMenusByRoleCodes(String roleCodes) {
        return menuService.findMenusByRoleCodes(roleCodes);
    }

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        return super.hasPermission(authentication, request.getMethod(), request.getRequestURI());
    }
}
