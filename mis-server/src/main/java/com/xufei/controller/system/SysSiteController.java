package com.xufei.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaIgnore;
import com.xufei.common.core.PageQuery;
import com.xufei.common.core.R;
import com.xufei.common.core.TableData;
import com.xufei.system.domain.SysSite;
import com.xufei.system.service.ISysSiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/system/site")
public class SysSiteController {

    private final ISysSiteService siteService;

    @SaIgnore
    @GetMapping("/all")
    public R<List<SysSite>> getAll() {
        List<SysSite> allSites = siteService.getAllSites();
        return R.ok(allSites);
    }

    @SaCheckPermission("system:site:list")
    @PostMapping("/list")
    public R<TableData<SysSite>> list(@RequestBody PageQuery<SysSite> pageQuery) {
        TableData<SysSite> tableData = siteService.selectPageUserList(pageQuery.getSearchData(), pageQuery);
        return R.ok(tableData);
    }

    @GetMapping("/{id}")
    public R<SysSite> getById(@PathVariable("id") Long id) {
        SysSite user = siteService.getById(id);
        return R.ok(user);
    }

    @PostMapping("/save")
    public R save(@RequestBody SysSite user) {
        siteService.save(user);
        return R.ok();
    }

    @PutMapping("/update")
    public R update(@RequestBody SysSite user) {
        siteService.update(user);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable("id") Long id) {
        siteService.deleteById(id);
        return R.ok();
    }
}
