package com.shl.oauth.openid;

import com.shl.common.constant.CommonConstant;
import com.shl.common.constant.SecurityConstants;
import com.shl.oauth.token.OpenIdAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
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
 * @author songhonglei
 * @version 1.0
 * @description
 * @date 2019/12/18
 */
public class OpenIdAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    /**
     * 请求中，参数为openid
     */
    private static final String OPEN_ID = "openid";
    private String openIdParameter = OPEN_ID;
    /**
     * 是否只处理post请求
     */
    private boolean postOnly = true;

    public OpenIdAuthenticationFilter() {
        //要拦截的请求
        super(new AntPathRequestMatcher(SecurityConstants.OPENID_TOKEN_URL, CommonConstant.POST_METHOD));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (this.postOnly && !CommonConstant.POST_METHOD.equals(request.getMethod())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());

        } else {
            String openId = this.obtainOpenId(request);
            if (openId == null) {
                openId = "";
            }
            openId = openId.trim();
            //把openId传进OpenIdAuthenticationToken
            OpenIdAuthenticationToken authRequest = new OpenIdAuthenticationToken(openId);
            this.setDetails(request, authRequest);
        }
        return null;
    }

    private void setDetails(HttpServletRequest request, OpenIdAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    private String obtainOpenId(HttpServletRequest request) {
        return request.getParameter(this.openIdParameter);
    }

    public void setOpenIdParameter(String openIdParameter) {
        Assert.hasText(openIdParameter, "openId parameter must not be empty or null");
        this.openIdParameter = openIdParameter;
    }


    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getOpenIdParameter() {
        return this.openIdParameter;
    }
}
