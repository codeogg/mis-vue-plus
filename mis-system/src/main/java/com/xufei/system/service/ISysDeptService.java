package com.xufei.system.service;

import com.xufei.common.core.PageQuery;
import com.xufei.common.core.TableData;
import com.xufei.system.domain.SysCompany;

public interface ISysDeptService {

    TableData<SysCompany> selectPageCompanyList(SysCompany searchData, PageQuery<SysCompany> pageQuery);
}
