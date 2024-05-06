package com.xufei.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xufei.common.core.PageQuery;
import com.xufei.common.core.TableData;
import com.xufei.common.exception.ServiceException;
import com.xufei.common.utils.StringUtil;
import com.xufei.system.domain.SysRole;
import com.xufei.system.domain.SysUser;
import com.xufei.system.domain.SysUserRole;
import com.xufei.system.mapper.SysRoleMapper;
import com.xufei.system.mapper.SysUserRoleMapper;
import com.xufei.system.service.ISysRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl implements ISysRoleService {

    private final SysRoleMapper baseMapper;
    private final SysUserRoleMapper userRoleMapper;

    @Override
    public Set<String> selectRolePermissionByUserId(Long userId, Long sitId) {
        List<SysRole> perms = baseMapper.selectRolePermsByUserId(userId, sitId);
        Set<String> permsSet = new HashSet<>();
        for (SysRole perm : perms) {
            if (ObjectUtil.isNotNull(perm)) {
                permsSet.add(perm.getRoleCode().trim());
            }
        }
        return permsSet;
    }

    @Override
    public TableData<SysRole> selectPageUserList(SysRole role, PageQuery<SysRole> pageQuery) {
        Page<SysRole> page = baseMapper.selectPage(pageQuery.build(), this.buildQueryWrapper(role));
        return TableData.build(page);
    }

    private QueryWrapper<SysRole> buildQueryWrapper(SysRole role) {
        QueryWrapper<SysRole> wrapper = Wrappers.query();

        wrapper.like(StringUtil.isNotBlank(role.getRoleName()), "role_name", role.getRoleName())
                .like(StringUtil.isNotBlank(role.getRoleCode()), "role_code", role.getRoleCode());

        return wrapper;
    }

    @Override
    public SysRole getById(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public void save(SysRole role) {
        if (isRoleNameExist(role)) {
            throw new ServiceException("角色名称已存在");
        }

        if (isRoleCodeExist(role)) {
            throw new ServiceException("角色编码已存在");
        }

        baseMapper.insert(role);
    }

    @Override
    public void update(SysRole role) {
        if (isRoleNameExist(role)) {
            throw new ServiceException("角色名称已存在");
        }

        if (isRoleCodeExist(role)) {
            throw new ServiceException("角色编码已存在");
        }
        baseMapper.updateById(role);
    }

    private boolean isRoleNameExist(SysRole role) {
        LambdaQueryWrapper<SysRole> lqw = new LambdaQueryWrapper<SysRole>()
                .eq(SysRole::getRoleName, role.getRoleName())
                .ne(ObjectUtil.isNotNull(role.getId()), SysRole::getId, role.getId());
        return baseMapper.exists(lqw);
    }

    private boolean isRoleCodeExist(SysRole role) {
        LambdaQueryWrapper<SysRole> lqw = new LambdaQueryWrapper<SysRole>()
                .eq(SysRole::getRoleCode, role.getRoleCode())
                .ne(ObjectUtil.isNotNull(role.getId()), SysRole::getId, role.getId());
        return baseMapper.exists(lqw);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        if (userRoleMapper.exists(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getRoleId, id))) {
            throw new ServiceException("此角色下还存在用户,不能删除");
        }
        userRoleMapper.deleteByRoleId(id);
        baseMapper.deleteById(id);
    }
}
