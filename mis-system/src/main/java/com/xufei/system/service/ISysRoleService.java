package com.xufei.system.service;

import java.util.Set;

public interface ISysRoleService {

    Set<String> selectRolePermissionByUserId(Long id,Long siteId);
}
