package com.xufei.controller.system;

import cn.dev33.satoken.annotation.SaIgnore;
import com.xufei.common.core.PageQuery;
import com.xufei.common.core.R;
import com.xufei.common.core.TableData;
import com.xufei.system.domain.SysDictType;
import com.xufei.system.service.ISysDictTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@SaIgnore
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/dict/type")
public class SysDictTypeController {

    private final ISysDictTypeService dictTypeService;

    @PostMapping("/list")
    public R<TableData<SysDictType>> list(@RequestBody PageQuery<SysDictType> pageQuery) {
        TableData<SysDictType> tableData = dictTypeService.selectPageList(pageQuery.getSearchData(), pageQuery);
        return R.ok(tableData);
    }

    @GetMapping("/{id}")
    public R<SysDictType> getById(@PathVariable("id") Long id) {
        SysDictType dictType = dictTypeService.getById(id);
        return R.ok(dictType);
    }

    @PostMapping("/save")
    public R save(@RequestBody SysDictType dictType) {
        dictTypeService.save(dictType);
        return R.ok();
    }

    @PutMapping("/update")
    public R update(@RequestBody SysDictType dictType) {
        dictTypeService.update(dictType);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable("id") Long id) {
        dictTypeService.deleteById(id);
        return R.ok();
    }
}
