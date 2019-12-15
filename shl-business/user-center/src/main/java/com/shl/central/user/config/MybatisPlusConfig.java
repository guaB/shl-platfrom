package com.shl.central.user.config;

import com.shl.common.config.DefaultMybatisPlusConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: songhonglei
 * @date: 2019-12-10
 */
@Configuration
@MapperScan("com.shl.central.user.mapper*")
public class MybatisPlusConfig extends DefaultMybatisPlusConfig {
}
