package com.xufei.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xufei.common.core.PageQuery;
import com.xufei.common.core.TableData;
import com.xufei.common.exception.ServiceException;
import com.xufei.common.utils.StringUtil;
import com.xufei.system.domain.SysCompany;
import com.xufei.system.domain.SysRole;
import com.xufei.system.mapper.SysCompanyMapper;
import com.xufei.system.mapper.SysDeptMapper;
import com.xufei.system.service.ISysCompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.management.Query;

@Slf4j
@Service
@RequiredArgsConstructor
public class SysCompanyServiceImpl implements ISysCompanyService {

    private final SysCompanyMapper baseMapper;

    @Override
    public TableData<SysCompany> selectPageCompanyList(SysCompany company, PageQuery<SysCompany> pageQuery) {
        Page<SysCompany> page = baseMapper.selectCompanyPage(pageQuery.build(), this.buildQueryWrapper(company));
        return TableData.build(page);
    }

    @Override
    public SysCompany getById(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public void update(SysCompany company) {

        if (isFullNameExist(company)) {
            throw new ServiceException("公司全称已存在");
        }

        if (isShortNameExist(company)) {
            throw new ServiceException("公司简称已存在");
        }

        baseMapper.insert(company);
    }

    private boolean isFullNameExist(SysCompany company) {
        LambdaQueryWrapper<SysCompany> lqw = new LambdaQueryWrapper<SysCompany>()
                .eq(SysCompany::getFullName, company.getFullName())
                .ne(ObjectUtil.isNotNull(company.getId()), SysCompany::getId, company.getId());
        return baseMapper.exists(lqw);
    }

    private boolean isShortNameExist(SysCompany company) {
        LambdaQueryWrapper<SysCompany> lqw = new LambdaQueryWrapper<SysCompany>()
                .eq(SysCompany::getShortName, company.getShortName())
                .ne(ObjectUtil.isNotNull(company.getId()), SysCompany::getId, company.getId());
        return baseMapper.exists(lqw);
    }


    private QueryWrapper<SysCompany> buildQueryWrapper(SysCompany company) {
        QueryWrapper<SysCompany> wrapper = Wrappers.query();
        wrapper.eq("d.parent_id", 0)
                .like(StringUtil.isNotBlank(company.getFullName()), "c.full_name", company.getFullName())
                .like(StringUtil.isNotBlank(company.getTin()), "c.tin", company.getTin())
                .like(StringUtil.isNotBlank(company.getAddress()), "c.address", company.getAddress())
                .like(StringUtil.isNotBlank(company.getLocation()), "c.location", company.getLocation())
                .like(StringUtil.isNotBlank(company.getShortName()), "c.short_name", company.getShortName())
                .eq(StringUtil.isNotBlank(company.getStatus()), "d.status", company.getStatus());
        return wrapper;
    }
}
