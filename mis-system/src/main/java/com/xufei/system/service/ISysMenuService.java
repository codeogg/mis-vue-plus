package com.xufei.system.service;

import com.xufei.common.core.LoginUser;
import com.xufei.common.core.vo.RouterVO;
import com.xufei.system.domain.SysMenu;
import com.xufei.system.domain.vo.TreeNode;

import java.util.List;
import java.util.Set;

public interface ISysMenuService {

    Set<String> selectMenuPermsByUserId(Long id,Long siteId);

    List<RouterVO> selectMenuTreeByUserId(LoginUser loginUser);

    List<SysMenu> getListBySiteId(Long siteId);

    SysMenu getById(Long id);

    void save(SysMenu menu);

    void update(SysMenu menu);

    void deleteById(Long id);

    List<TreeNode> getMenuTreeForRole(Long siteId, Long roleId);

    List<TreeNode> getMenuTreeForUser(Long siteId, Long userId);

    List<TreeNode> getMenuTree(Long siteId);
}
