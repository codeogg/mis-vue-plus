package com.xufei.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xufei.common.core.PageQuery;
import com.xufei.common.core.TableData;
import com.xufei.common.utils.StringUtil;
import com.xufei.system.domain.SysDictData;
import com.xufei.system.domain.SysDictType;
import com.xufei.system.mapper.SysDictDataMapper;
import com.xufei.system.service.ISysDictDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SysDictDataServiceImpl implements ISysDictDataService {

    private final SysDictDataMapper baseMapper;

    @Override
    public TableData<SysDictData> selectPageList(SysDictData dictData, PageQuery<SysDictData> pageQuery) {
        Page<SysDictData> page = baseMapper.selectPage(pageQuery.build(), this.buildQueryWrapper(dictData));
        return TableData.build(page);
    }

    private QueryWrapper<SysDictData> buildQueryWrapper(SysDictData dictData) {
        QueryWrapper<SysDictData> wrapper = Wrappers.query();

        wrapper.like(StringUtil.isNotBlank(dictData.getDictType()), "dict_type", dictData.getDictType())
                .like(StringUtil.isNotBlank(dictData.getDictLabel()), "dict_label", dictData.getDictLabel())
                .like(StringUtil.isNotBlank(dictData.getDictValue()), "dict_value", dictData.getDictValue());

        return wrapper;
    }

    @Override
    public SysDictData getById(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public void save(SysDictData dictData) {
        baseMapper.insert(dictData);
    }

    @Override
    public void update(SysDictData dictData) {
        baseMapper.updateById(dictData);
    }

    @Override
    public void deleteById(Long id) {
        baseMapper.deleteById(id);
    }
}
