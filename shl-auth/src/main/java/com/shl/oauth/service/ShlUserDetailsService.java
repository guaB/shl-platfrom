package com.shl.oauth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @description:
 * @author: songhonglei
 * @date: 2019-12-03
 */
public interface ShlUserDetailsService  extends UserDetailsService {

    /**
     * 根据电话号码查询用户
     *
     * @param mobile
     * @return
     */
    UserDetails loadUserByMobile(String mobile);
}
