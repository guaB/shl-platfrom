package com.shl.common.feign.fallback;

import com.shl.common.feign.UserService;
import com.shl.common.model.LoginAppUser;
import com.shl.common.model.SysUser;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @description: userService降级工场
 * @author: songhonglei
 * @date: 2019-12-11
 */
@Slf4j
@Component("userService")
public class UserServiceFallbackFactory implements FallbackFactory<UserService> {
    @Override
    public UserService create(Throwable throwable) {
        return new UserService() {
            @Override
            public SysUser selectByUsername(String username) {
                log.error("通过用户名查询用户异常:{}", username, throwable);
                return new SysUser();
            }

            @Override
            public LoginAppUser findByUsername(String username) {
                log.error("通过用户名查询用户异常:{}", username, throwable);
                return new LoginAppUser();
            }

            @Override
            public LoginAppUser findByMobile(String mobile) {
                log.error("通过手机号查询用户异常:{}", mobile, throwable);
                return new LoginAppUser();
            }

            @Override
            public LoginAppUser findByOpenId(String openId) {
                log.error("通过openId查询用户异常:{}", openId, throwable);
                return new LoginAppUser();
            }
        };
    }
}
