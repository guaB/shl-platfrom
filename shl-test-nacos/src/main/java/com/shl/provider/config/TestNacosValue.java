package com.shl.provider.config;

import java.time.LocalDateTime;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Date;

/**
 * @description:
 * @author: songhonglei
 * @date: 2019-11-04
 */
@Data
@ConfigurationProperties(value = "tom")
public class TestNacosValue {
    @JSONField(name = "userName")
    private String name;
    @JSONField(name = "userAge")
    private int age;
    private String sex;
    private Date date;
}