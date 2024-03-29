package com.shl.oauth.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author songhonglei
 * @version 1.0
 * @description
 * @date 2019/12/8
 */
@Getter
@Setter
public class AuthProperties {

    /**
     * 配置要认证的url（默认不需要配置）
     *
     * 优先级大于忽略认证配置`shl.security.ignore.httpUrls`
     * 意思是如果同一个url同时配置了`忽略认证`和`需要认证`，则该url还是会被认证
     *
     * */
    private String[] httpUrls = {};


    /**
     * url权限配置
     */
    private UrlPermissionProperties urlPermission = new UrlPermissionProperties();
}
