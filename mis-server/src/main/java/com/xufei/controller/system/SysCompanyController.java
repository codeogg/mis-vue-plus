package com.xufei.controller.system;

import cn.dev33.satoken.annotation.SaIgnore;
import com.xufei.common.core.PageQuery;
import com.xufei.common.core.R;
import com.xufei.common.core.TableData;
import com.xufei.system.domain.SysCompany;
import com.xufei.system.domain.SysRole;
import com.xufei.system.domain.SysSite;
import com.xufei.system.service.ISysCompanyService;
import com.xufei.system.service.ISysDeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@SaIgnore
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/company")
public class SysCompanyController {

    private final ISysCompanyService companyService;

    @PostMapping("/list")
    public R<TableData<SysCompany>> list(@RequestBody PageQuery<SysCompany> pageQuery){
        TableData<SysCompany> tableData = companyService.selectPageCompanyList(pageQuery.getSearchData(), pageQuery);
        return R.ok(tableData);
    }

    @GetMapping("/{id}")
    public R<SysCompany> getById(@PathVariable("id") Long id) {
        SysCompany company = companyService.getById(id);
        return R.ok(company);
    }

    @PutMapping("/update")
    public R update(@RequestBody SysCompany company) {
        companyService.update(company);
        return R.ok();
    }


}
