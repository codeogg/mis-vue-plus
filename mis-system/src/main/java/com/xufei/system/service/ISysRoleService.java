package com.xufei.system.service;

import com.xufei.common.core.PageQuery;
import com.xufei.common.core.TableData;
import com.xufei.system.domain.SysRole;
import com.xufei.system.domain.dto.AssignRoleMenuDto;
import com.xufei.system.domain.vo.RoleRepVo;

import java.util.List;
import java.util.Set;

public interface ISysRoleService {

    Set<String> selectRolePermissionByUserId(Long id,Long siteId);

    TableData<SysRole> selectPageUserList(SysRole role, PageQuery<SysRole> pageQuery);

    SysRole getById(Long id);

    void save(SysRole user);

    void update(SysRole user);

    void deleteById(Long id);

    void assignMenu(AssignRoleMenuDto roleMenuDto);

    List<RoleRepVo> getAll(Long userId);

    List<Long> getAssignedRoleMenuIds(Long siteId, Long roleId);
}
