package com.xufei.controller.system;

import cn.dev33.satoken.annotation.SaIgnore;
import com.xufei.common.core.PageQuery;
import com.xufei.common.core.R;
import com.xufei.common.core.TableData;
import com.xufei.system.domain.SysRole;
import com.xufei.system.service.ISysRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@SaIgnore
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/role")
public class SysRoleController {

    private final ISysRoleService roleService;

    @PostMapping("/list")
    public R<TableData<SysRole>> list(@RequestBody PageQuery<SysRole> pageQuery) {
        TableData<SysRole> tableData = roleService.selectPageUserList(pageQuery.getSearchData(), pageQuery);
        return R.ok(tableData);
    }

    @GetMapping("/{id}")
    public R<SysRole> getById(@PathVariable("id") Long id) {
        SysRole user = roleService.getById(id);
        return R.ok(user);
    }

    @PostMapping("/save")
    public R save(@RequestBody SysRole user) {
        roleService.save(user);
        return R.ok();
    }

    @PutMapping("/update")
    public R update(@RequestBody SysRole user) {
        roleService.update(user);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable("id") Long id) {
        roleService.deleteById(id);
        return R.ok();
    }
}
