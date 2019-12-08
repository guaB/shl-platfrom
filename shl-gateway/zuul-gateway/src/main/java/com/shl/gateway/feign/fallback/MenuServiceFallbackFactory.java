package com.shl.gateway.feign.fallback;

import com.google.common.collect.Lists;
import com.shl.gateway.feign.MenuService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author songhonglei
 * @version 1.0
 * @description menuService降级工场
 * @date 2019/12/8
 */
@Slf4j
@Component
public class MenuServiceFallbackFactory implements FallbackFactory<MenuService> {
    @Override
    public MenuService create(Throwable throwable) {
        return roleIds -> {
            log.error("调用findByRoleCodes异常：{}", roleIds, throwable);
            return Lists.newArrayList();
        };
    }
}
