package com.shl.oauth2.stroe;

import com.shl.common.constant.CommonConstant;
import com.shl.common.constant.SecurityConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author songhonglei
 * @version 1.0
 * @description 资源服务器 TokenStore 配置类，使用 JWT RSA 非对称加密
 * @date 2019/12/8
 */
public class ResJwtTokenStore {

    @Autowired
    private ResourceServerProperties resource;

    @Bean
    public TokenStore tokenStore(JwtAccessTokenConverter jwtAccessTokenConverter){
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setVerifierKey(getPubKey());
        return converter;
    }

    /**
     * 获取非对称加密公钥 Key
     * @return 公钥 Key
     */
    private String getPubKey() {
        Resource resource = new ClassPathResource(SecurityConstants.RSA_PUBLIC_KEY.getMsg());
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))){
            return br.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            return getKeyFromAuthorizationServer();
        }
    }

    /**
     * 通过访问授权服务器获取非对称加密公钥 Key
     * @return 公钥 Key
     */
    private String getKeyFromAuthorizationServer() {
        if(StringUtils.isNotEmpty(this.resource.getJwt().getKeyUri())){
            HttpHeaders httpHeaders = new HttpHeaders();
            String clientId = this.resource.getClientId();
            String clientSecret = this.resource.getClientSecret();
            if(StringUtils.isNotEmpty(clientId) && StringUtils.isNotEmpty(clientSecret)){
                byte[] token = Base64.getEncoder().encode((clientId.concat(":").concat(clientSecret)).getBytes());
                httpHeaders.add(CommonConstant.TOKEN_HEADER.getMsg(), CommonConstant.BASIC.getMsg() + new String(token));
            }
            final HttpEntity<Void> request = new HttpEntity<>(httpHeaders);
            String url = this.resource.getJwt().getKeyUri();
            return (String)new RestTemplate().exchange(url, HttpMethod.GET, request, Map.class).getBody().get("value");
        }
        return null;
    }
}
