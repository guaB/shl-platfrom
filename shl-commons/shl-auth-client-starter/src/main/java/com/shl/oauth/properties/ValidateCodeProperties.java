package com.shl.oauth.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @description:
 * @author: songhonglei
 * @date: 2019-12-19
 */
@Setter
@Getter
public class ValidateCodeProperties {
    /**
     * 设置认证通时不需要验证码的clientId
     */
    private String[] ignoreClientCode = {};
}
