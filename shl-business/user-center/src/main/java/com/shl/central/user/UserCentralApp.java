package com.shl.central.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description:
 * @author: songhonglei
 * @date: 2019-12-10
 */
@SpringBootApplication
@EnableDiscoveryClient
public class UserCentralApp {
    public static void main(String[] args) {
        SpringApplication.run(UserCentralApp.class, args);
    }
}
