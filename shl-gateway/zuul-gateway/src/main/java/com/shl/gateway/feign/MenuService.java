package com.shl.gateway.feign;

import com.shl.common.model.SysMenu;
import com.shl.gateway.feign.fallback.MenuServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author songhonglei
 * @version 1.0
 * @description 菜单查询
 * @date 2019/12/8
 */
@FeignClient(name = "user-center", fallback = MenuServiceFallbackFactory.class, decode404 = true)
public interface MenuService {

    @GetMapping(value = "/menus/{roleCodes}")
    List<SysMenu> findByRoleCodes(@PathVariable("roleCodes") String roleCodes);
}
