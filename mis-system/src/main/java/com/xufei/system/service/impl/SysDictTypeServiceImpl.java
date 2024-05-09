package com.xufei.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xufei.common.core.PageQuery;
import com.xufei.common.core.TableData;
import com.xufei.common.exception.ServiceException;
import com.xufei.common.utils.StringUtil;
import com.xufei.system.domain.SysDictData;
import com.xufei.system.domain.SysDictType;
import com.xufei.system.domain.SysRole;
import com.xufei.system.mapper.SysDictDataMapper;
import com.xufei.system.mapper.SysDictTypeMapper;
import com.xufei.system.service.ISysDictTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SysDictTypeServiceImpl implements ISysDictTypeService {

    private final SysDictTypeMapper baseMapper;
    private final SysDictDataMapper dictDataMapper;

    @Override
    public TableData<SysDictType> selectPageList(SysDictType dictType, PageQuery<SysDictType> pageQuery) {
        Page<SysDictType> page = baseMapper.selectPage(pageQuery.build(), this.buildQueryWrapper(dictType));
        return TableData.build(page);
    }

    private QueryWrapper<SysDictType> buildQueryWrapper(SysDictType dictType) {
        QueryWrapper<SysDictType> wrapper = Wrappers.query();

        wrapper.like(StringUtil.isNotBlank(dictType.getDictName()), "dict_name", dictType.getDictName())
                .like(StringUtil.isNotBlank(dictType.getDictType()), "dict_type", dictType.getDictType())
                .like(StringUtil.isNotBlank(dictType.getRemark()), "remark", dictType.getRemark());

        return wrapper;
    }

    @Override
    public SysDictType getById(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public void save(SysDictType dictType) {
        if (isDictNameExist(dictType)) {
            throw new ServiceException("字典名称已存在");
        }

        if (isDictTypeExist(dictType)) {
            throw new ServiceException("字典类型已存在");
        }

        baseMapper.insert(dictType);
    }

    private boolean isDictNameExist(SysDictType dictType) {
        LambdaQueryWrapper<SysDictType> lqw = new LambdaQueryWrapper<SysDictType>()
                .eq(SysDictType::getDictName, dictType.getDictName())
                .ne(ObjectUtil.isNotNull(dictType.getId()), SysDictType::getId, dictType.getId());
        return baseMapper.exists(lqw);
    }

    private boolean isDictTypeExist(SysDictType dictType) {
        LambdaQueryWrapper<SysDictType> lqw = new LambdaQueryWrapper<SysDictType>()
                .eq(SysDictType::getDictType, dictType.getDictType())
                .ne(ObjectUtil.isNotNull(dictType.getId()), SysDictType::getId, dictType.getId());
        return baseMapper.exists(lqw);
    }

    @Transactional
    @Override
    public void update(SysDictType dictType) {
        if (isDictNameExist(dictType)) {
            throw new ServiceException("字典名称已存在");
        }

        if (isDictTypeExist(dictType)) {
            throw new ServiceException("字典类型已存在");
        }

        // 获取原字典类型
        String oldDictType = this.getById(dictType.getId()).getDictType();

        // 当更改了字典类型,先把字典类型的从表里的数据先更新
        if (!StringUtil.equals(oldDictType, dictType.getDictType())) {
            dictDataMapper.update(new LambdaUpdateWrapper<SysDictData>()
                    .eq(SysDictData::getDictType, oldDictType)
                    .set(SysDictData::getDictType, dictType.getDictType()));
        }

        baseMapper.updateById(dictType);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        SysDictType dictType = this.getById(id);
        if (dictType == null) {
            throw new ServiceException("要删除的数据不存在");
        }
        // 先删除从表数据
        dictDataMapper.delete(new LambdaUpdateWrapper<SysDictData>().eq(SysDictData::getDictType, dictType.getDictType()));
        baseMapper.deleteById(id);
    }
}
