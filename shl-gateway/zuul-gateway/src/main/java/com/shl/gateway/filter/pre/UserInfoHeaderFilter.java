package com.shl.gateway.filter.pre;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.shl.common.constant.SecurityConstants;
import com.shl.common.model.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.FORM_BODY_WRAPPER_FILTER_ORDER;

/**
 * @author songhonglei
 * @version 1.0
 * @description 将认证用户的相关信息放入header中, 后端服务可以直接读取使用
 * @date 2019/12/8
 */
@Component
public class UserInfoHeaderFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FORM_BODY_WRAPPER_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            SysUser user = (SysUser) authentication.getPrincipal();
            Long userId = user.getId();
            String username = user.getUsername();

            OAuth2Authentication auth2Authentication = (OAuth2Authentication) authentication;
            String clientId = auth2Authentication.getOAuth2Request().getClientId();

            RequestContext ctx = RequestContext.getCurrentContext();
            ctx.addZuulRequestHeader(SecurityConstants.USER_ID_HEADER, userId.toString());
            ctx.addZuulRequestHeader(SecurityConstants.USER_HEADER, username);
            ctx.addZuulRequestHeader(SecurityConstants.TENANT_HEADER, clientId);
            ctx.addZuulRequestHeader(SecurityConstants.ROLE_HEADER, StringUtils.join(authentication.getAuthorities(), ","));

        }
        return null;
    }
}
