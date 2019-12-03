package com.shl.provider.controller;

import java.awt.PageAttributes.MediaType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.shl.provider.config.TestNacosValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: songhonglei
 * @date: 2019-11-04
 */
@RestController
@Slf4j
@EnableConfigurationProperties({TestNacosValue.class})
public class TestNacosController {

    @Autowired
    TestNacosValue testNacosValue;

    @Value("${tom.name}")
    private String name;

    @GetMapping("/user1")
    public Object getUserOne() {
        log.info("name: " + testNacosValue);
        return testNacosValue;
    }

    @GetMapping("/user2")
    public TestNacosValue getUserTwo() {
        TestNacosValue testNacosValue = new TestNacosValue();
        testNacosValue.setName(name);
        testNacosValue.setDate(new Date());
        log.info("name: " + name);
        return testNacosValue;
    }
}
