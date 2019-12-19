package com.shl.oauth.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @description: 验证码异常
 * @author: songhonglei
 * @date: 2019-12-19
 */
public class ValidateCodeException extends AuthenticationException {
    private static final long serialVersionUID = -7285211528095468156L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
