package com.shl.oauth.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.annotation.Resource;
import javax.servlet.Filter;

/**
 * @description: 验证码
 * @author: songhonglei
 * @date: 2019-12-19
 */
@Configuration
public class ValidateCodeSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    @Qualifier("validateImageCodeFilter")
    private Filter validateImageCodeFilter;

    @Autowired
    @Qualifier("validateSmsCodeFilter")
    private Filter validateSmsCodeFilter;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(validateImageCodeFilter, AbstractPreAuthenticatedProcessingFilter.class)
                .addFilterAfter(validateSmsCodeFilter, AbstractPreAuthenticatedProcessingFilter.class);
    }
}
