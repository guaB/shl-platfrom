package com.shl.gateway.config;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.shl.gateway.route.nacos.NacosDynRouteLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletPath;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author songhonglei
 * @version 1.0
 * @description 动态路由配置
 * @date 2019/12/22
 */
@Configuration
@ConditionalOnProperty(prefix = "shl.gateway.dynamicRoute", name = "enabled", havingValue = "true")
public class DynamicZuulRouteConfig {

    @Autowired
    private ZuulProperties zuulProperties;

    @Autowired
    private DispatcherServletPath dispatcherServletPath;

    /**
     * Nacos配置
     * */
    @Configuration
    @ConditionalOnProperty(prefix = "shl.gateway.dynamicRoute", name = "dataType", havingValue = "nacos", matchIfMissing = true)
    public class NacosZuulRoute{

        @Autowired
        private NacosConfigProperties nacosConfigProperties;

        @Autowired
        private ApplicationEventPublisher publisher;

        @Bean
        public NacosDynRouteLocator nacosDynRouteLocator(){
            return new NacosDynRouteLocator(nacosConfigProperties, publisher, dispatcherServletPath.getPath(), zuulProperties);
        }
    }
}
