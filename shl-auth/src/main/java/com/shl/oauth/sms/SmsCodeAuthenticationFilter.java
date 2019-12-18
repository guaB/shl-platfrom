package com.shl.oauth.sms;

import com.shl.common.constant.CommonConstant;
import com.shl.oauth.token.SmsCodeAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: songhonglei
 * @date: 2019-12-18
 */
public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    /**
     * 请求中，参数为mobile
     */
    private static final String MOBILE_KEY = "mobile";
    private String mobileParameter = MOBILE_KEY;
    /**
     * 是否只处理post请求
     */
    private boolean postOnly = true;

    public SmsCodeAuthenticationFilter() {
        //要拦截的请求
        super(new AntPathRequestMatcher(CommonConstant.SMS_AUTH_URL, CommonConstant.POST_METHOD));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (this.postOnly && !CommonConstant.POST_METHOD.equals(request.getMethod())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            String mobile = this.obtainMobile(request);
            if (mobile == null) {
                mobile = "";
            }
            mobile = mobile.trim();
            //把手n机号传进SmsCodeAuthenticationToken
            SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(mobile);
            this.setDetails(request, authRequest);
            //调用AuthenticationManager
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }

    private void setDetails(HttpServletRequest request, SmsCodeAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    /**
     * 获取手机号
     *
     * @param request request
     * @return String
     */
    private String obtainMobile(HttpServletRequest request) {
        return request.getParameter(this.mobileParameter);
    }
}
