package com.xufei.system.service;

import com.xufei.system.domain.SysUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class SysPermissionService {

    private final ISysMenuService menuService;
    private final ISysRoleService roleService;


    public Set<String> getMenuPermissions(SysUser user, Long siteId) {
        Set<String> perms = new HashSet<>();
        // 管理员拥有所有权限
        if (user.isAdmin()) {
            perms.add("*:*:*");
        } else {
            perms.addAll(menuService.selectMenuPermsByUserId(user.getId(),siteId));
        }
        return perms;

    }

    public Set<String> getRolePermissions(SysUser user, Long siteId) {
        Set<String> roles = new HashSet<>();
        // 管理员拥有所有权限
        if (user.isAdmin()) {
            roles.add("admin");
        } else {
            roles.addAll(roleService.selectRolePermissionByUserId(user.getId(),siteId));
        }
        return roles;
    }
}
