package com.xufei.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xufei.common.core.PageQuery;
import com.xufei.common.core.TableData;
import com.xufei.common.utils.StringUtil;
import com.xufei.system.domain.SysRole;
import com.xufei.system.domain.SysSite;
import com.xufei.system.mapper.SysSiteMapper;
import com.xufei.system.service.ISysSiteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SysSiteServiceImpl implements ISysSiteService {

    private final SysSiteMapper baseMapper;

    @Override
    public List<SysSite> getAllSites() {
        List<SysSite> sites = this.baseMapper.selectList(null);
        return sites;
    }

    @Override
    public TableData<SysSite> selectPageUserList(SysSite site, PageQuery<SysSite> pageQuery) {
        Page<SysSite> page = baseMapper.selectPage(pageQuery.build(), this.buildQueryWrapper(site));
        return TableData.build(page);
    }

    private QueryWrapper<SysSite> buildQueryWrapper(SysSite site) {
        QueryWrapper<SysSite> wrapper = Wrappers.query();

        wrapper.like(StringUtil.isNotBlank(site.getSiteName()), "site_name", site.getSiteName())
                .like(StringUtil.isNotBlank(site.getWebsite()), "website", site.getWebsite());

        return wrapper;
    }

    @Override
    public SysSite getById(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public void save(SysSite site) {
        baseMapper.insert(site);
    }

    @Override
    public void update(SysSite site) {
        baseMapper.updateById(site);
    }

    @Override
    public void deleteById(Long id) {
        baseMapper.deleteById(id);
    }

}
