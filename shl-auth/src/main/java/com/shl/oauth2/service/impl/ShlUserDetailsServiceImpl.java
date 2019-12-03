package com.shl.oauth2.service.impl;

import com.shl.oauth2.model.LoginAppUser;
import com.shl.oauth2.service.ShlUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: songhonglei
 * @date: 2019-12-03
 */
@Slf4j
@Service
public class ShlUserDetailsServiceImpl implements ShlUserDetailsService, SocialUserDetailsService {


    @Override
    public UserDetails loadUserByMobile(String mobile) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public SocialUserDetails loadUserByUserId(String s) throws UsernameNotFoundException {
        return null;
    }

    private LoginAppUser checkUser(LoginAppUser loginAppUser) {
        if (loginAppUser != null && !loginAppUser.isEnabled()) {
            throw new DisabledException("用户已作废");
        }
        return loginAppUser;
    }
}
