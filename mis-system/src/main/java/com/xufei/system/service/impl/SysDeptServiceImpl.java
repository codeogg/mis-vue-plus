package com.xufei.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xufei.common.core.PageQuery;
import com.xufei.common.core.TableData;
import com.xufei.common.exception.ServiceException;
import com.xufei.common.utils.StringUtil;
import com.xufei.system.domain.SysCompany;
import com.xufei.system.domain.SysDept;
import com.xufei.system.domain.SysUser;
import com.xufei.system.mapper.SysDeptMapper;
import com.xufei.system.mapper.SysUserMapper;
import com.xufei.system.service.ISysDeptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SysDeptServiceImpl implements ISysDeptService {

    private final SysDeptMapper baseMapper;
    private final SysUserMapper userMapper;


    @Override
    public void save(SysDept dept) {
        if (isDeptNameExist(dept)) {
            throw new ServiceException("该节点下部门名称已存在");
        }

        Long id = getNextId(dept);
        dept.setId(id);

        Long count = baseMapper.selectCount(new LambdaQueryWrapper<SysDept>().eq(SysDept::getParentId, dept.getParentId()));
        dept.setSortNum(count.intValue() + 1);

        baseMapper.insert(dept);
    }

    private Long getNextId(SysDept dept) {
        if (dept.getType() == 0) {
            return baseMapper.getCompanyMaxId(dept.getParentId());
        } else if (dept.getType() == 1) {
            return baseMapper.getDeptMaxId(dept.getParentId());
        } else if (dept.getType() == 2) {
            return baseMapper.getGroupMaxId(dept.getParentId());
        }else {
            throw new ServiceException("该部门的类型不被支持");
        }
    }

    @Override
    public SysDept getById(Long deptId) {
        return baseMapper.selectById(deptId);
    }

    @Override
    public List<SysDept> selectDeptList(SysDept dept) {
        LambdaQueryWrapper<SysDept> lqw = new LambdaQueryWrapper<SysDept>()
                .eq(SysDept::getStatus, "0")
                .like(StringUtil.isNotBlank(dept.getDeptName()), SysDept::getDeptName, dept.getDeptName())
                .orderByAsc(SysDept::getSortNum);
        return baseMapper.selectList(lqw);
    }

    @Override
    public void update(SysDept dept) {
        if (isDeptNameExist(dept)) {
            throw new ServiceException("该节点下部门名称已存在");
        }

        baseMapper.updateById(dept);
    }

    @Override
    public void deleteById(Long id) {

        // 查询这个部门以及其子部门的id
        List<Long> deptIds = getDeptIdsWithChildren(id);
        if (userMapper.exists(new LambdaQueryWrapper<SysUser>().in(SysUser::getDeptId, deptIds))) {
            throw new ServiceException("该部门以及子部门下存在用户,不能删除");
        }

        baseMapper.deleteBatchIds(deptIds);
    }

    public List<Long> getDeptIdsWithChildren(Long id) {

        List<Long> ids = baseMapper.selectObjs(new LambdaQueryWrapper<SysDept>()
                .eq(SysDept::getParentId, id)
                .select(SysDept::getId));
        ids.add(id);
        return ids;
    }

    private boolean isDeptNameExist(SysDept dept) {
        LambdaQueryWrapper<SysDept> lqw = new LambdaQueryWrapper<SysDept>()
                .eq(SysDept::getParentId, dept.getParentId())
                .eq(SysDept::getDeptName, dept.getDeptName())
                .ne(ObjectUtil.isNotNull(dept.getId()), SysDept::getId, dept.getId());
        return baseMapper.exists(lqw);
    }
}
