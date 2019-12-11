package com.shl.central.user.controller;

import com.google.common.collect.Lists;
import com.shl.central.user.service.ISysMenuService;
import com.shl.common.model.SysMenu;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: songhonglei
 * @date: 2019-12-11
 */
@RestController
@RequestMapping("/menus")
public class SysMenuController {

    @Autowired
    private ISysMenuService menuService;

    @GetMapping("/{roleCodes}")
    public List<SysMenu> findMenuByRoles(@PathVariable String roleCodes) {
        List<SysMenu> result = null;
        if (StringUtils.isNoneBlank(roleCodes)) {
            Set<String> roleSet = Lists.newArrayList(roleCodes.split(",")).stream().collect(Collectors.toSet());
            result = menuService.findByRoleCodes(roleSet, 2);
        }

        return result;
    }
}
