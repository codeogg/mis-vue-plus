package com.xufei.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xufei.common.constants.CommonConstants;
import com.xufei.common.core.LoginUser;
import com.xufei.common.core.vo.RouterVO;
import com.xufei.common.helper.LoginHelper;
import com.xufei.common.utils.StringUtil;
import com.xufei.system.domain.SysMenu;
import com.xufei.system.domain.SysUserMenu;
import com.xufei.system.mapper.SysMenuMapper;
import com.xufei.system.mapper.SysUserMenuMapper;
import com.xufei.system.service.ISysMenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl implements ISysMenuService {

    private final SysMenuMapper baseMapper;
    private final SysUserMenuMapper userMenuMapper;

    @Override
    public Set<String> selectMenuPermsByUserId(Long userId, Long siteId) {
        // 已拥有的权限
        List<String> perms = baseMapper.selectMenuPermsByUserId(userId, siteId);
        // 该用户禁止的权限
        List<String> disabledMenus = userMenuMapper.selectDisabledMenuByUserId(userId, siteId);

        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtil.isNoneEmpty(perm)) {
                permsSet.add(perm.trim());
            }
        }

        return permsSet.stream().filter(perm -> !disabledMenus.contains(perm)).collect(Collectors.toSet());
    }

    @Override
    public List<RouterVO> selectMenuTreeByUserId(LoginUser loginUser) {
        List<SysMenu> menus = null;

        String userName = loginUser.getUserName();

        if (LoginHelper.isAdmin(userName)) {
            // 查询这个网站的所有菜单
            menus = baseMapper.selectList(new LambdaQueryWrapper<SysMenu>()
                    .eq(SysMenu::getSiteId, loginUser.getSiteId()));
        }else{
            menus = baseMapper.selectMenusByUserId(loginUser.getUserId(), loginUser.getSiteId()));
        }


        return null;
    }
}
