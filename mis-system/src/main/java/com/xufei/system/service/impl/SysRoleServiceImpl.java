package com.xufei.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.xufei.system.domain.SysRole;
import com.xufei.system.mapper.SysRoleMapper;
import com.xufei.system.service.ISysRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl implements ISysRoleService {

    private final SysRoleMapper baseMapper;

    @Override
    public Set<String> selectRolePermissionByUserId(Long userId,Long sitId) {
        List<SysRole> perms = baseMapper.selectRolePermsByUserId(userId,sitId);
        Set<String> permsSet = new HashSet<>();
        for (SysRole perm : perms) {
            if (ObjectUtil.isNotNull(perm)) {
                permsSet.add(perm.getRoleCode().trim());
            }
        }
        return permsSet;
    }
}
