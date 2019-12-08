package com.shl.gateway.config;

import com.shl.common.config.DefaultPasswordConfig;
import com.shl.oauth2.config.DefaultResourceServerConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author songhonglei
 * @version 1.0
 * @description 资源服务OAuth2配置
 * @date 2019/12/8
 */
@Configuration
@EnableResourceServer
@Import({DefaultPasswordConfig.class})
public class ResourceServerConfig extends DefaultResourceServerConfig {
    @Override
    public HttpSecurity setAuthenticate(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.AuthorizedUrl authorizedUrl) {
        return authorizedUrl.access("@permissionServiceImpl.hasPermission(request, authentication)").and();
    }
}
