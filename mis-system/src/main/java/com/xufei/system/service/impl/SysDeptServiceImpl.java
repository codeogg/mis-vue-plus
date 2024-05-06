package com.xufei.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xufei.common.core.PageQuery;
import com.xufei.common.core.TableData;
import com.xufei.system.domain.SysCompany;
import com.xufei.system.mapper.SysDeptMapper;
import com.xufei.system.service.ISysDeptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SysDeptServiceImpl implements ISysDeptService {

    private final SysDeptMapper baseMapper;

    @Override
    public TableData<SysCompany> selectPageCompanyList(SysCompany searchData, PageQuery<SysCompany> pageQuery) {
        return null;
    }
}
