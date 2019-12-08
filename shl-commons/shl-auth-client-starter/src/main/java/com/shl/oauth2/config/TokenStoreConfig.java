package com.shl.oauth2.config;

import com.shl.oauth2.stroe.AuthDbTokenStore;
import com.shl.oauth2.stroe.AuthJwtTokenStore;
import com.shl.oauth2.stroe.AuthRedisTokenStroe;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author songhonglei
 * @version 1.0
 * @description 加载TokenStore配置
 * @date 2019/12/7
 */
@Configuration
public class TokenStoreConfig {

    @Configuration
    @ConditionalOnProperty(prefix = "shl.oauth2.token.store", name = "type", havingValue = "db")
    @Import(AuthDbTokenStore.class)
    public class JdbcTokenConfig {
    }

    @Configuration
    @ConditionalOnProperty(prefix = "shl.oauth2.token.store", name = "type", havingValue = "redis")
    @Import(AuthRedisTokenStroe.class)
    public class RedisTokenConfig {
    }

    @Configuration
    @ConditionalOnProperty(prefix = "shl.oauth2.token.store", name = "type", havingValue = "authJwt")
    @Import(AuthJwtTokenStore.class)
    public class AuthJwtTokenConfig{
    }

    @Configuration
    @ConditionalOnProperty(prefix = "shl.oauth2.token.store", name = "type", havingValue = "resJwt")
    @Import(AuthJwtTokenStore.class)
    public class ResJwtTokenConfig{
    }
}
