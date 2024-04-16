package com.xufei.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xufei.common.constants.CommonConstants;
import com.xufei.common.core.LoginUser;
import com.xufei.common.core.vo.MetaVO;
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
        } else {
            menus = baseMapper.selectMenusByUserId(loginUser.getUserId(), loginUser.getSiteId());
        }

        List<SysMenu> menuTree = buildTree(menus, 0L);
        List<RouterVO> routes = buildRoutes(menuTree);
        return routes;
    }

    private List<RouterVO> buildRoutes(List<SysMenu> menus) {
        List<RouterVO> routes = new LinkedList<>();
        for (SysMenu menu : menus) {
            RouterVO router = new RouterVO();
            router.setHidden(ObjectUtil.equal(0, menu.getVisible()));
            router.setName(StringUtil.capitalize(menu.getPath()));
            router.setPath(menu.getPath());
            router.setComponent(menu.getComponent());
            router.setMeta(new MetaVO(menu.getMenuName(), menu.getIcon(), ObjectUtil.equal(0, menu.getKeepAlive())));

            List<SysMenu> children = menu.getChildren();
            if (ObjectUtil.equal(2, menu.getMenuType())) {
                List<SysMenu> hiddenMenuList = children.stream()
                        .filter(item -> StringUtil.isNotEmpty(item.getComponent())).collect(Collectors.toList());

                for (SysMenu hiddenMenu : hiddenMenuList) {
                    RouterVO hiddenRouter = new RouterVO();
                    hiddenRouter.setHidden(true);
                    hiddenRouter.setAlwaysShow(false);
                    hiddenRouter.setPath(hiddenMenu.getPath());
                    hiddenRouter.setComponent(hiddenMenu.getComponent());
                    hiddenRouter.setMeta(new MetaVO(hiddenMenu.getMenuName(), hiddenMenu.getIcon(), ObjectUtil.equal(0, menu.getKeepAlive())));
                    routes.add(hiddenRouter);
                }
            } else {
                if (!CollUtil.isEmpty(children)) {
                    if (children.size() > 0) {
                        router.setAlwaysShow(true);
                    }
                    router.setChildren(buildRoutes(children));
                }
            }

            routes.add(router);

        }
        return routes;
    }

    private List<SysMenu> buildTree(List<SysMenu> list, Long parentId) {
        return list.stream()
                .filter(menu -> ObjectUtil.equal(menu.getParentId(), parentId))
                .map(menu -> {
                    menu.setChildren(buildTree(list, menu.getId()));
                    return menu;
                })
                .collect(Collectors.toList());
    }
}
