package com.xufei.system.test;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xufei.common.core.vo.RouterVO;
import com.xufei.system.domain.SysMenu;
import com.xufei.system.domain.SysUser;
import com.xufei.system.mapper.SysMenuMapper;
import com.xufei.system.mapper.SysUserMapper;
import com.xufei.system.service.ISysMenuService;
import com.xufei.system.service.ISysSiteService;
import com.xufei.system.service.impl.SysMenuServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class ServiceTest {

    @Autowired
    private SysMenuMapper  menuMapper;
    @Autowired
    SysMenuServiceImpl menuService;

    @Test
    void test01() {
        List<SysMenu> menus = menus = menuMapper.selectList(new LambdaQueryWrapper<SysMenu>()
                .in(SysMenu::getMenuType, 1, 2)
                .eq(SysMenu::getSiteId, 1));

        List<SysMenu> menuTree = menuService.buildTree(menus, 0L);
        List<RouterVO> routes = menuService.buildRoutes(menuTree);

        for (RouterVO route : routes) {
            System.out.println(route);
        }

    }
}
