package com.shl.oauth.mobile;

import com.shl.oauth.token.MobileAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: songhonglei
 * @date: 2019-12-18
 */
public class MobileAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final String SPRING_SECURITY_FORM_MOBILE_KEY = "mobile";
    private static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";

    private String mobileParameter = SPRING_SECURITY_FORM_MOBILE_KEY;
    private String passwordParameter = SPRING_SECURITY_FORM_PASSWORD_KEY;
    private boolean postOnly = true;

    public MobileAuthenticationFilter() {
        super(new AntPathRequestMatcher("/login/mobile", "POST"));
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if(this.postOnly && !request.getMethod().equals("POST")){
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }else {
            String mobile = this.obtainMobile(request);
            String password = this.obtainPassword(request);
            if (mobile == null){
                mobile = "";
            }
            if (password == null){
                password = "";

            }
            mobile = mobile.trim();
            //把手机号传进MobileAuthenticationToken
            MobileAuthenticationToken authRequest = new MobileAuthenticationToken(mobile, password);
            // Allow subclasses to set the "details" property
            this.setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }

    }

    private void setDetails(HttpServletRequest request, MobileAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    /**
     * 获取密码
     * @param request
     * @return
     */
    private String obtainPassword(HttpServletRequest request) {
        return request.getParameter(this.passwordParameter);
    }

    /**
     * 获取手机号
     * @param request
     * @return
     */
    private String obtainMobile(HttpServletRequest request) {
        return request.getParameter(this.mobileParameter);
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public void setMobileParameter(String mobileParameter) {
        Assert.hasText(mobileParameter, "mobile parameter must not be empty or null");
        this.mobileParameter = mobileParameter;
    }

    public void setPasswordParameter(String passwordParameter) {
        Assert.hasText(passwordParameter, "Password parameter must not be empty or null");
        this.passwordParameter = passwordParameter;
    }

    public final String getMobileParameter() {
        return mobileParameter;
    }

    public final String getPasswordParameter() {
        return passwordParameter;
    }
}
