package com.xufei.controller.system;

import cn.dev33.satoken.annotation.SaIgnore;
import com.xufei.common.core.PageQuery;
import com.xufei.common.core.R;
import com.xufei.common.core.TableData;
import com.xufei.system.domain.SysRole;
import com.xufei.system.domain.dto.AssignRoleMenuDto;
import com.xufei.system.domain.vo.RoleMenuReqVo;
import com.xufei.system.domain.vo.RoleRepVo;
import com.xufei.system.service.ISysRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/all")
    public R<List<RoleRepVo>> getAll(Long userId) {
        List<RoleRepVo> list = roleService.getAll(userId);
        return R.ok(list);
    }

    @GetMapping("/{id}")
    public R<SysRole> getById(@PathVariable("id") Long id) {
        SysRole role = roleService.getById(id);
        return R.ok(role);
    }

    @PostMapping("/save")
    public R save(@RequestBody SysRole role) {
        roleService.save(role);
        return R.ok();
    }

    @PutMapping("/update")
    public R update(@RequestBody SysRole role) {
        roleService.update(role);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable("id") Long id) {
        roleService.deleteById(id);
        return R.ok();
    }

    @PostMapping("/assign")
    public R assign(@RequestBody AssignRoleMenuDto roleMenuDto) {
        roleService.assignMenu(roleMenuDto);
        return R.ok();
    }

    @PostMapping("/assigned/menu")
    public R<List<Long>> getAssignedRoleMenuIds(@RequestBody RoleMenuReqVo roleMenuReqVo) {
        List<Long> ids = roleService.getAssignedRoleMenuIds(roleMenuReqVo.getSiteId(), roleMenuReqVo.getRoleId());
        return R.ok(ids);
    }
}
