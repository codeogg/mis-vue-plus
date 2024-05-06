package com.xufei.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xufei.system.domain.SysCompany;
import org.apache.ibatis.annotations.Param;

public interface SysCompanyMapper extends BaseMapper<SysCompany> {

    Page<SysCompany> selectCompanyPage(@Param("page") Page<SysCompany> page, @Param(Constants.WRAPPER) QueryWrapper<SysCompany> queryWrapper);
}
