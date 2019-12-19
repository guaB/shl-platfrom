package com.shl.oauth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shl.common.constant.SecurityConstants;
import com.shl.common.utils.ResponseUtil;
import com.shl.oauth.token.MobileAuthenticationToken;
import com.shl.oauth.token.OpenIdAuthenticationToken;
import com.shl.oauth.token.SmsCodeAuthenticationToken;
import com.shl.oauth.util.AuthUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: songhonglei
 * @date: 2019-12-19
 */
@Slf4j
@RestController
public class OAuth2Controller {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Resource
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @PostMapping(SecurityConstants.PASSWORD_LOGIN_PRO_URL)
    public void getUserTokenInfo(@RequestParam("username") String username, @RequestParam("password") String password,
                                 HttpServletRequest request, HttpServletResponse response) throws IOException {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        writerToken(request, response, token, "用户名或密码错误");
    }

    @PostMapping(SecurityConstants.OPENID_TOKEN_URL)
    public void getTokenByOpenId(@RequestParam("openId") String openId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        OpenIdAuthenticationToken token = new OpenIdAuthenticationToken(openId);
        writerToken(request, response, token, "openId错误");
    }

    @PostMapping(SecurityConstants.MOBILE_TOKEN_URL)
    public void getTokenByMobile(@RequestParam("mobile") String mobile, @RequestParam("password") String password,
                                 HttpServletRequest request, HttpServletResponse response) throws IOException {
        MobileAuthenticationToken token = new MobileAuthenticationToken(mobile, password);
        writerToken(request, response, token, "手机号或密码错误");
    }

    @PostMapping(SecurityConstants.SMS_TOKEN_URL)
    public void getTokenBySms(@RequestParam("mobile") String mobile,
                                 HttpServletRequest request, HttpServletResponse response) throws IOException {
        SmsCodeAuthenticationToken token = new SmsCodeAuthenticationToken(mobile);
        writerToken(request, response, token, "手机号或验证错误");
    }

    private void writerToken(HttpServletRequest request, HttpServletResponse response,
                             AbstractAuthenticationToken token, String badCredentialsMag) throws IOException {
        try {
            String[] client = AuthUtils.extractClient(request);
            String clientId = client[0];
            String clientSecret = client[1];

            ClientDetails clientDetails = getClient(clientId, clientSecret);

            TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_MAP, clientId, clientDetails.getScope(), "customer");
            OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);

            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
            OAuth2AccessToken oAuth2AccessToken = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
            oAuth2Authentication.setAuthenticated(true);

            ResponseUtil.responseSucceed(objectMapper, response, oAuth2AccessToken);
        }  catch (BadCredentialsException | InternalAuthenticationServiceException e) {
            exceptionHandler(response, badCredentialsMag);
        } catch (Exception e) {
            exceptionHandler(response, e);
        }
    }

    private void exceptionHandler(HttpServletResponse response, Exception e) throws IOException {
        log.error("exceptionHandler-error:", e);
        exceptionHandler(response, e.getMessage());
    }

    private void exceptionHandler(HttpServletResponse response, String badCredentialsMag) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        ResponseUtil.responseFailed(objectMapper, response, badCredentialsMag);
    }

    private ClientDetails getClient(String clientId, String clientSecret) {
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);

        if (clientDetails == null) {
            throw new UnapprovedClientAuthenticationException("clientId对应的信息不存在");
        } else if (!passwordEncoder.matches(clientSecret, clientDetails.getClientSecret())) {
            throw new UnapprovedClientAuthenticationException("clientSecret不匹配");
        }
        return clientDetails;
    }
}
