package com.shl.oauth.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @author songhonglei
 * @version 1.0
 * @description
 * @date 2019/12/7
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "shl.security")
@RefreshScope
public class SecurityProperties {
    private AuthProperties auth = new AuthProperties();

    private PermitProperties ignore = new PermitProperties();

}
