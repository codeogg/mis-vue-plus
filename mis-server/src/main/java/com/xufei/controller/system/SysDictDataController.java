package com.xufei.controller.system;

import cn.dev33.satoken.annotation.SaIgnore;
import com.xufei.common.core.PageQuery;
import com.xufei.common.core.R;
import com.xufei.common.core.TableData;
import com.xufei.system.domain.SysDictData;
import com.xufei.system.service.ISysDictDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@SaIgnore
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/dict/data")
public class SysDictDataController {

    private final ISysDictDataService dictDataService;

    @PostMapping("/list")
    public R<TableData<SysDictData>> list(@RequestBody PageQuery<SysDictData> pageQuery) {
        TableData<SysDictData> tableData = dictDataService.selectPageList(pageQuery.getSearchData(), pageQuery);
        return R.ok(tableData);
    }

    @GetMapping("/{id}")
    public R<SysDictData> getById(@PathVariable("id") Long id) {
        SysDictData dictData = dictDataService.getById(id);
        return R.ok(dictData);
    }

    @PostMapping("/save")
    public R save(@RequestBody SysDictData dictData) {
        dictDataService.save(dictData);
        return R.ok();
    }

    @PutMapping("/update")
    public R update(@RequestBody SysDictData dictData) {
        dictDataService.update(dictData);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable("id") Long id) {
        dictDataService.deleteById(id);
        return R.ok();
    }
    
}
