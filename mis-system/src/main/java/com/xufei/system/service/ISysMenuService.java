package com.xufei.system.service;

import com.xufei.common.core.LoginUser;
import com.xufei.common.core.vo.RouterVO;
import com.xufei.system.domain.SysMenu;

import java.util.List;
import java.util.Set;

public interface ISysMenuService {

    Set<String> selectMenuPermsByUserId(Long id,Long siteId);

    List<RouterVO> selectMenuTreeByUserId(LoginUser loginUser);

    List<SysMenu> getListBySiteId(Long siteId);
}
