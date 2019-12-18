package com.shl.oauth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shl.oauth.util.AuthUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

/**
 * @description: 自定义Oauth2认证器
 * @author: songhonglei
 * @date: 2019-12-18
 */
@Component
@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Resource
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        // 1. 获取请求头部中的ClientId
        String[] tokens = AuthUtils.extractClient(request);
        String clientId = tokens[0];
        String clientSecret = tokens[1];

        // 2. 通过ClientDetailsService获取 ClientDetails
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);

        //3. 校验ClientId 和 ClientSecret的正确性
        TokenRequest tokenRequest = null;
        if (Objects.isNull(clientDetails)){
            throw new UnapprovedClientAuthenticationException("clientId" + clientId + "对应的信息不存在");
        }else if (!StringUtils.equals(clientDetails.getClientSecret(), clientSecret)){
            throw new UnapprovedClientAuthenticationException("clientSecret不正确");
        }else {
            // 4. 通过TokenRequest构造器生成 TokenRequest
            tokenRequest = new TokenRequest(new HashMap<>(), clientId, clientDetails.getScope(), "custom");
        }

        //5. 通过TokenRequest的createOAuth2Request方法获取OAuth2Request
        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);

        //6. 通过Authentication和OAuth2Request 构造OAuth2Authentication
        OAuth2Authentication auth2Authentication  = new OAuth2Authentication(oAuth2Request, authentication);

        //7. 通过 AuthorizationServerTokenServices 生成 OAuth2AccessToken
        OAuth2AccessToken accessToken = authorizationServerTokenServices.createAccessToken(auth2Authentication);

        response.setContentType(MediaType.APPLICATION_JSON_UTF8.getType());
        response.getWriter().write(new ObjectMapper().writeValueAsString(accessToken));
    }
}
