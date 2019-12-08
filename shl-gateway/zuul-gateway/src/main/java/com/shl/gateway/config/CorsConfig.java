package com.shl.gateway.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author songhonglei
 * @version 1.0
 * @description 跨域配置
 * @date 2019/12/8
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        // #允许向该服务器提交请求的URI，*表示全部允许
        config.addAllowedOrigin(CorsConfiguration.ALL);
        // 允许cookies跨域
        config.setAllowCredentials(true);
        // 允许访问头部信息, * 表示全部
        config.addAllowedHeader(CorsConfiguration.ALL);
        // 允许提交请求方式， * 表示全部允许
        config.addAllowedMethod(CorsConfiguration.ALL);
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    /**
     * 配置过滤器
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean<CorsFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(corsFilter());
        registration.addUrlPatterns("/*");
        registration.setName("corsFilter");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registration;
    }
}
