package com.xufei.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xufei.common.core.LoginUser;
import com.xufei.common.core.vo.MetaVO;
import com.xufei.common.core.vo.RouterVO;
import com.xufei.common.exception.ServiceException;
import com.xufei.common.helper.LoginHelper;
import com.xufei.common.utils.StringUtil;
import com.xufei.system.domain.SysMenu;
import com.xufei.system.domain.SysRoleMenu;
import com.xufei.system.domain.SysUserMenu;
import com.xufei.system.domain.vo.TreeNode;
import com.xufei.system.mapper.SysMenuMapper;
import com.xufei.system.mapper.SysRoleMenuMapper;
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
    private final SysRoleMenuMapper roleMenuMapper;

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
//                    .in(SysMenu::getMenuType, 1, 2)
                    .eq(SysMenu::getSiteId, loginUser.getSiteId())
                    .orderByAsc(SysMenu::getSortNum));
        } else {
            menus = baseMapper.selectMenusByUserId(loginUser.getUserId(), loginUser.getSiteId());
        }

        LoginHelper.refreshLoginUser(loginUser);

        List<SysMenu> menuTree = buildTree(menus, 0L);
        List<RouterVO> routes = buildRoutes(menuTree);
        return routes;
    }

    @Override
    public List<SysMenu> getListBySiteId(Long siteId) {
        return baseMapper.selectList(new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getSiteId, siteId));
    }

    @Override
    public SysMenu getById(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public void save(SysMenu menu) {
        if (menu.getMenuType().equals("1")) {
            if (menu.getParentId().equals("0")) {
                menu.setComponent("Layout");
            } else {
                menu.setComponent("ParentView");
            }
        }
        baseMapper.insert(menu);
    }

    @Override
    public void update(SysMenu menu) {
        baseMapper.updateById(menu);
    }

    @Override
    public void deleteById(Long id) {
        if (baseMapper.exists(new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getParentId, id))) {
            throw new ServiceException("该菜单下存在子节点,不能删除");
        }
        baseMapper.deleteById(id);
    }

    /**
     * 角色分配菜单用,根据siteId查询所有菜单,并且回显这个角色已分配菜单
     *
     * @param siteId 门户ID
     * @param roleId 角色ID
     * @return
     */
    @Override
    public List<TreeNode> getMenuTreeForRole(Long siteId, Long roleId) {
        // 根据siteId查询某个门户系统的所有菜单
        List<SysMenu> menus = baseMapper.selectList(new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getSiteId, siteId));

        // 查询roleId已分配的菜单ID
        LambdaQueryWrapper<SysRoleMenu> lqw = new LambdaQueryWrapper<SysRoleMenu>()
                .eq(SysRoleMenu::getSiteId, siteId)
                .eq(SysRoleMenu::getRoleId, roleId)
                .select(SysRoleMenu::getMenuId);
        List<Long> roleMenuIds = roleMenuMapper.selectObjs(lqw);

        // 将菜单全部转换成TreeNode
        List<TreeNode> nodes = menus.stream().map(item -> {
            TreeNode treeNode = new TreeNode(item.getId(), item.getMenuName(), item.getParentId(), siteId);
            // treeNode.setChecked(roleMenuIds.contains(item.getId()));
            return treeNode;
        }).collect(Collectors.toList());

        // 将List转换成Tree
        return buildTree2(nodes, 0L);
    }

    /**
     * 用户分配菜单用,根据siteId查询所有菜单,并且回显这个用户已分配菜单,注意有加减权限
     *
     * @param siteId
     * @param userId
     * @return
     */
    @Override
    public List<TreeNode> getMenuTreeForUser(Long siteId, Long userId) {
        // 根据siteId查询某个门户系统的所有菜单
        List<SysMenu> menus = baseMapper.selectList(new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getSiteId, siteId));

        // 查询roleId已分配的菜单ID
        LambdaQueryWrapper<SysUserMenu> lqw = new LambdaQueryWrapper<SysUserMenu>()
                .eq(SysUserMenu::getSiteId, siteId)
                .eq(SysUserMenu::getUserId, userId);
        List<SysUserMenu> userMenus = userMenuMapper.selectObjs(lqw);

        Map<Long, Integer> map = new HashMap<>();
        for (SysUserMenu userMenu : userMenus) {
            map.put(userMenu.getMenuId(), userMenu.getType());
        }


        // 将菜单全部转换成TreeNode
        List<TreeNode> nodes = menus.stream().map(item -> {
            Long menuId = item.getId();
            TreeNode treeNode = new TreeNode(menuId, item.getMenuName(), item.getParentId(), siteId);
            if (map.containsKey(menuId)) {
                Integer type = map.get(menuId);
                // treeNode.setChecked(true);
                // treeNode.setExtra(type);
            }
            return treeNode;
        }).collect(Collectors.toList());

        // 将List转换成Tree
        return buildTree2(nodes, 0L);
    }

    @Override
    public List<TreeNode> getMenuTree(Long siteId) {
        // 根据siteId查询某个门户系统的所有菜单
        List<SysMenu> menus = baseMapper.selectList(new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getSiteId, siteId));

        // 将菜单全部转换成TreeNode
        List<TreeNode> nodes = menus.stream().map(item -> new TreeNode(item.getId(), item.getMenuName(), item.getParentId(), siteId))
                .collect(Collectors.toList());

        return buildTree2(nodes, 0L);
    }

    public List<RouterVO> buildRoutes(List<SysMenu> menus) {
        List<RouterVO> routes = new LinkedList<>();
        for (SysMenu menu : menus) {
            RouterVO router = new RouterVO();
            router.setHidden(false);
            router.setAlwaysShow(false);
//            router.setName(StringUtil.capitalize(menu.getPath()));
            router.setPath(getRouterPath(menu));
            router.setComponent(menu.getComponent());
//            router.setRedirect("noRedirect");
            router.setMeta(new MetaVO(menu.getMenuName(), menu.getIcon(), menu.getKeepAlive().intValue() == 0));

            List<SysMenu> children = menu.getChildren();
            if (ObjectUtil.equal("2", menu.getMenuType())) {
//                List<SysMenu> hiddenMenuList = children.stream()
//                        .filter(item -> StringUtil.isNotEmpty(item.getComponent())).collect(Collectors.toList());

                List<SysMenu> hiddenMenuList = children.stream()
                        .filter(item -> item.getVisible().intValue() == 0).collect(Collectors.toList());

                for (SysMenu hiddenMenu : hiddenMenuList) {
                    RouterVO hiddenRouter = new RouterVO();
                    hiddenRouter.setHidden(true);
                    hiddenRouter.setAlwaysShow(false);
                    hiddenRouter.setPath(hiddenMenu.getPath());
                    hiddenRouter.setComponent(hiddenMenu.getComponent());
                    hiddenRouter.setMeta(new MetaVO(hiddenMenu.getMenuName(), hiddenMenu.getIcon(), menu.getKeepAlive().intValue() == 0));
                    routes.add(hiddenRouter);
                }
            } else {
                if (CollUtil.isNotEmpty(children)) {
                    router.setAlwaysShow(true);
                    router.setChildren(buildRoutes(children));
                }
            }

            routes.add(router);
        }
        return routes;
    }

    public String getRouterPath(SysMenu menu) {
        return (menu.getParentId().longValue() == 0L ? "/" : "") + menu.getPath();
    }

    public List<SysMenu> buildTree(List<SysMenu> list, Long parentId) {
        return list.stream()
                .filter(menu -> ObjectUtil.equal(menu.getParentId(), parentId))
                .map(menu -> {
                    menu.setChildren(buildTree(list, menu.getId()));
                    return menu;
                })
                .collect(Collectors.toList());
    }

    public List<TreeNode> buildTree2(List<TreeNode> list, Long parentId) {
        return list.stream()
                .filter(node -> ObjectUtil.equal(node.getParentId(), parentId))
                .map(node -> {
                    node.setChildren(buildTree2(list, node.getId()));
                    return node;
                })
                .collect(Collectors.toList());
    }

}
