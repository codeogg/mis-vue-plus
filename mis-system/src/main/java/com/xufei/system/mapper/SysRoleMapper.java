package com.xufei.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xufei.system.domain.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRole> selectRolePermsByUserId(@Param("userId") Long userId, @Param("sitId") Long sitId);
}
