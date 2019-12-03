package com.shl.oauth2.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @description: 认证错误处理
 * @author: songhonglei
 * @date: 2019-11-26
 */
@Configuration
@Slf4j
public class SecurityHandlerConfig {

    @Resource
    private ObjectMapper objectMapper;


}
