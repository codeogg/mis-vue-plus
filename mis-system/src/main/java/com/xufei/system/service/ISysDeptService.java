package com.xufei.system.service;

import com.xufei.common.core.PageQuery;
import com.xufei.common.core.TableData;
import com.xufei.system.domain.SysDept;

import java.util.List;

public interface ISysDeptService {

    void save(SysDept dept);

    SysDept getById(Long deptId);

    List<SysDept> selectDeptList(SysDept dept);

    void update(SysDept dept);

    void deleteById(Long id);
}
