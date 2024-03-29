package com.shl.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description:
 * @author: songhonglei
 * @date: 2019-11-04
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ProviderApp  {
    public static void main(String[] args) {
        SpringApplication.run(ProviderApp.class, args);
    }
}
