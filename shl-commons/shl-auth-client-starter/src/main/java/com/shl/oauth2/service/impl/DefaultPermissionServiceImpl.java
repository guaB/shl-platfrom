package com.shl.oauth2.service.impl;

import com.shl.common.constant.CommonConstant;
import com.shl.common.model.SysMenu;
import com.shl.oauth2.properties.SecurityProperties;
import com.shl.oauth2.util.AuthUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author songhonglei
 * @version 1.0
 * @description 请求权限判断service
 * @date 2019/12/8
 */
@Slf4j
public abstract class DefaultPermissionServiceImpl {

    @Autowired
    private SecurityProperties securityProperties;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 查询当前用户拥有的资源权限
     *
     * @param roleCodes 角色code列表，多个以','隔开
     * @return
     */
    public abstract List<SysMenu> findMenusByRoleCodes(String roleCodes);

    public boolean hasPermission(Authentication authentication, String requestMethod, String requestURI) {
        //前端跨域OPTIONS请求预检测放行  也可以通过前端配置代理实现
        if (HttpMethod.OPTIONS.name().equalsIgnoreCase(requestMethod)) {
            return true;
        }

        //非匿名认证用户进行权限验证
        if (!(authentication instanceof AnonymousAuthenticationToken)) {

            //判断是否开启url权限验证
            if (!securityProperties.getAuth().getUrlPermission().getEnable()) {
                return true;
            }

            //超级管理员admin不需要认证
            String username = AuthUtils.getUsername(authentication);
            if (CommonConstant.ADMIN_USER_NAME.equalsIgnoreCase(username)) {
                return true;
            }

            //判断不进行url权限认证的api，所有已登录用户都能访问的url
            for (String path : securityProperties.getAuth().getUrlPermission().getIgnoreUrls()){
                if (antPathMatcher.match(path, requestURI)){
                    return true;
                }
            }

            List<SimpleGrantedAuthority> grantedAuthorityList  = (List<SimpleGrantedAuthority>) authentication.getAuthorities();
            if (CollectionUtils.isEmpty(grantedAuthorityList)){
                log.warn("角色列表为空：{}", authentication.getPrincipal());
                return false;
            }

            String roleCodes = grantedAuthorityList.stream().map(SimpleGrantedAuthority::getAuthority).collect(Collectors.joining(","));
            List<SysMenu> menuList = findMenusByRoleCodes(roleCodes);
            for (SysMenu menu: menuList){
                if (StringUtils.isNotEmpty(menu.getUrl()) && antPathMatcher.match(menu.getUrl(), requestURI)){
                    if (StringUtils.isNotEmpty(menu.getPathMethod())){
                        return requestMethod.equalsIgnoreCase(menu.getPathMethod());
                    }else {
                        return true;
                    }
                }
            }
        }

        return false;
    }

}
