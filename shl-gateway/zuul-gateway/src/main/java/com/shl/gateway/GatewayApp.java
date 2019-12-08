package com.shl.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author songhonglei
 * @version 1.0
 * @description 网关代理
 * @date 2019/12/8
 */
@EnableZuulProxy
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApp {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApp.class, args);
    }
}
