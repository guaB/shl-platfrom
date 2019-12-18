package com.shl.oauth.sms;

import com.shl.oauth.service.ShlUserDetailsService;
import com.shl.oauth.token.SmsCodeAuthenticationToken;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @description:
 * @author: songhonglei
 * @date: 2019-12-18
 */
@Setter
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    private ShlUserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken)authentication;
        String mobile = (String) authenticationToken.getPrincipal();
        UserDetails user = userDetailsService.loadUserByMobile(mobile);
        if (user == null){
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }
        SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(mobile, user.getAuthorities());
        authenticationResult.setDetails(authenticationToken.getDetails());
        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
