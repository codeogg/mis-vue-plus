package com.xufei.controller.system;

import cn.dev33.satoken.annotation.SaIgnore;
import com.xufei.common.core.PageQuery;
import com.xufei.common.core.R;
import com.xufei.common.core.TableData;
import com.xufei.system.domain.SysUser;
import com.xufei.system.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/user")
public class SysUserController {

    private final ISysUserService userService;

    @SaIgnore
    @PostMapping("/list")
    public R<TableData<SysUser>> list(@RequestBody PageQuery<SysUser> pageQuery) {
        TableData<SysUser> tableData = userService.selectPageUserList(pageQuery.getSearchData(), pageQuery);
        return R.ok(tableData);
    }

    @SaIgnore
    @GetMapping("/{id}")
    public R<SysUser> getById(@PathVariable("id") Long id) {
        SysUser user = userService.getById(id);
        return R.ok(user);
    }

    @SaIgnore
    @PostMapping("/save")
    public R save(@RequestBody SysUser user) {
        userService.save(user);
        return R.ok();
    }

    @SaIgnore
    @PutMapping("/update")
    public R update(@RequestBody SysUser user) {
        userService.update(user);
        return R.ok();
    }

    @SaIgnore
    @DeleteMapping("/{id}")
    public R delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return R.ok();
    }

    @SaIgnore
    @PostMapping("/updateStatus")
    public R updateStatus(@RequestBody List<Long> ids) {
        userService.updateStatus(ids);
        return R.ok();
    }


    @SaIgnore
    @PostMapping("/import")
    public R importData(MultipartFile file) throws Exception {
        String result = userService.importData(file);
        return R.ok(result);
    }

    @SaIgnore
    @PostMapping("/export")
    public void exportData(@RequestBody SysUser searchData, HttpServletResponse response) {
        userService.exportData(searchData, response);
    }
}
