package com.xufei.controller.system;

import com.xufei.common.core.PageQuery;
import com.xufei.common.core.R;
import com.xufei.common.core.TableData;
import com.xufei.system.domain.SysCompany;
import com.xufei.system.service.ISysCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/save")
    public R save(@RequestBody SysCompany company) {
        companyService.save(company);
        return R.ok();
    }

    @GetMapping("/{deptId}")
    public R<SysCompany> getById(@PathVariable("deptId") Long deptId) {
        SysCompany company = companyService.getByDeptId(deptId);
        return R.ok(company);
    }

    @PutMapping("/update")
    public R update(@RequestBody SysCompany company) {
        companyService.update(company);
        return R.ok();
    }


}
