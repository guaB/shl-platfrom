package com.shl.gateway.config;

import lombok.Getter;
import lombok.Setter;

/**
 * @author songhonglei
 * @version 1.0
 * @description 动态路由配置
 * @date 2019/12/22
 */
@Setter
@Getter
public class ZuulRouteEntity {
    private String id;
    private String path;
    private String serviceId;
    private String url;
    private boolean stripPrefix;
    private Boolean retryable;
    private String apiName;
    private boolean enabled = true;
    private boolean customSensitiveHeaders = true;
}
