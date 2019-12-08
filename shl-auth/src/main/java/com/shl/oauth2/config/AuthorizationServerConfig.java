package com.shl.oauth2.config;

import com.shl.oauth2.service.impl.RedisClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Objects;

/**
 * @description:
 * @author: songhonglei
 * @date: 2019-11-25
 */
@Configuration
@EnableAuthorizationServer
@AutoConfigureAfter(AuthorizationServerEndpointsConfigurer.class)
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    /**
     * 注入authenticationManager 来支持 password grant type
     */
    @Autowired
    private  AuthenticationManager authenticationManager;

    @Autowired
    private TokenStore tokenStore;

    @Autowired(required = false)
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired(required = false)
    private TokenEnhancer tokenEnhancer;

    @Resource
    private UserDetailsService userDetailsService;

    @Autowired
    private RedisClientDetailsService clientDetailsService;

    @Autowired
    private WebResponseExceptionTranslator webResponseExceptionTranslator;

    @Autowired
    RandomValueAuthorizationCodeServices authorizationCodeServices;
    /**
     * 配置身份认证器，配置认证方式，TokenStore，TokenGranter，OAuth2RequestFactory
     * @param endpoints
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        if (!Objects.isNull(jwtAccessTokenConverter)){
            if (!Objects.isNull(tokenEnhancer)){
                TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
                tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer, jwtAccessTokenConverter));
                endpoints.tokenEnhancer(tokenEnhancerChain);
            }else {
                endpoints.accessTokenConverter(jwtAccessTokenConverter);
            }
        }
        endpoints.tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .authorizationCodeServices(authorizationCodeServices)
                .exceptionTranslator(webResponseExceptionTranslator);
    }

    /**
     * 配置应用名称 应用id
     * 配置OAuth2的客户端相关信息
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
        clientDetailsService.loadAllClientToCache();
    }

    /**
     * 对应于配置AuthorizationServer安全认证的相关信息，创建ClientCredentialsTokenEndpointFilter核心过滤器
     * @param security
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("isAuthenticated()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();//让/oauth/token支持client_id以及client_secret作登录认证
    }
}
