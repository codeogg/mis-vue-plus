package com.xufei.controller.system;

import cn.dev33.satoken.annotation.SaIgnore;
import com.xufei.common.core.R;
import com.xufei.system.domain.SysMenu;
import com.xufei.system.service.ISysMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SaIgnore
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/menu")
public class SysMenuController {

    private final ISysMenuService menuService;

    @GetMapping("/list/{siteId}")
    public R<List<SysMenu>> list(@PathVariable("siteId") Long siteId){
        List<SysMenu> list = menuService.getListBySiteId(siteId);
        return R.ok(list);
    }
}
