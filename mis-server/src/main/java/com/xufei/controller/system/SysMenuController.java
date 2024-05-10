package com.xufei.controller.system;

import com.xufei.common.core.R;
import com.xufei.system.domain.SysMenu;
import com.xufei.system.domain.vo.TreeNode;
import com.xufei.system.service.ISysMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/system/menu")
public class SysMenuController {

    private final ISysMenuService menuService;

    @GetMapping("/list/{siteId}")
    public R<List<SysMenu>> list(@PathVariable("siteId") Long siteId) {
        List<SysMenu> list = menuService.getListBySiteId(siteId);
        return R.ok(list);
    }

    @GetMapping("/tree/{siteId}")
    public R<List<TreeNode>> getTreeForRole(@PathVariable("siteId") Long siteId){
        List<TreeNode> tree = menuService.getMenuTree(siteId);
        return R.ok(tree);
    }

    @GetMapping("/{id}")
    public R<SysMenu> getById(@PathVariable("id") Long id) {
        SysMenu menu = menuService.getById(id);
        return R.ok(menu);
    }
    
    @PostMapping("/save")
    public R save(@RequestBody SysMenu menu) {
        menuService.save(menu);
        return R.ok();
    }

    @PutMapping("/update")
    public R update(@RequestBody SysMenu menu) {
        menuService.update(menu);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable("id") Long id) {
        menuService.deleteById(id);
        return R.ok();
    }

}
