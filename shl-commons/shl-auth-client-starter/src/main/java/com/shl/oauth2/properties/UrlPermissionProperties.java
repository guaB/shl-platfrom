package com.shl.oauth2.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author songhonglei
 * @version 1.0
 * @description
 * @date 2019/12/8
 */
@Setter
@Getter
public class UrlPermissionProperties {

    /**
     * 是否开启url级别权限
     */
    private Boolean enable = false;

    /**
     * 配置只进行登录认证，不进行url权限认证的api
     * 所有已登录的人都能访问的api
     */
    private String[] ignoreUrls = {};
}
