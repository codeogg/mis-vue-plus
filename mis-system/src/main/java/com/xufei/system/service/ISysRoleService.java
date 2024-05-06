package com.xufei.system.service;

import com.xufei.common.core.PageQuery;
import com.xufei.common.core.TableData;
import com.xufei.system.domain.SysRole;

import java.util.Set;

public interface ISysRoleService {

    Set<String> selectRolePermissionByUserId(Long id,Long siteId);

    TableData<SysRole> selectPageUserList(SysRole role, PageQuery<SysRole> pageQuery);

    SysRole getById(Long id);

    void save(SysRole user);

    void update(SysRole user);

    void deleteById(Long id);
}
