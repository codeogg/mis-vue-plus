package com.xufei.system.service;

import com.xufei.common.core.PageQuery;
import com.xufei.common.core.TableData;
import com.xufei.system.domain.SysCompany;

public interface ISysCompanyService {

    TableData<SysCompany> selectPageCompanyList(SysCompany company, PageQuery<SysCompany> pageQuery);

    void update(SysCompany company);

    void save(SysCompany company);

    SysCompany getByDeptId(Long deptId);
}
