package com.xufei.controller.system;

import cn.dev33.satoken.annotation.SaIgnore;
import com.mysql.cj.xdevapi.Table;
import com.xufei.common.core.PageQuery;
import com.xufei.common.core.R;
import com.xufei.common.core.TableData;
import com.xufei.system.domain.SysDept;
import com.xufei.system.service.ISysDeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SaIgnore
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/dept")
public class SysDeptController {

    private final ISysDeptService deptService;

    @PostMapping("/list")
    public R<List<SysDept>> list(@RequestBody SysDept dept) {
        List<SysDept> list = deptService.selectDeptList(dept);
        return R.ok(list);
    }

    @GetMapping("/{id}")
    public R<SysDept> getById(@PathVariable("id") Long id) {
        SysDept dept = deptService.getById(id);
        return R.ok(dept);
    }

    @PostMapping("/save")
    public R save(@RequestBody SysDept dept) {
        deptService.save(dept);
        return R.ok();
    }

    @PutMapping("/update")
    public R update(@RequestBody SysDept dept) {
        deptService.update(dept);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable("id") Long id) {
        deptService.deleteById(id);
        return R.ok();
    }
}
