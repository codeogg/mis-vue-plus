package com.xufei.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xufei.system.domain.SysDept;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SysDeptMapper extends BaseMapper<SysDept> {

    @Select("SELECT IFNULL(MAX(id),#{parentId}) + 100 FROM sys_dept WHERE parent_id = #{parentId}")
    Long getCompanyMaxId(@Param("parentId") Long parentId);

    @Select("SELECT IFNULL(MAX(id),#{parentId}) + 1 FROM sys_dept WHERE parent_id = #{parentId}")
    Long getDeptMaxId(@Param("parentId") Long parentId);

    @Select("SELECT IFNULL(MAX(id),#{parentId} * 100) + 1 FROM sys_dept WHERE parent_id = #{parentId}")
    Long getGroupMaxId(@Param("parentId") Long parentId);
}
