package com.xufei.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xufei.system.domain.SysUserRole;
import org.apache.ibatis.annotations.Delete;

public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    @Delete("delete from sys_user_role where user_id = #{userId}")
    void deleteByUserId(Long userId);

    @Delete("delete from sys_user_role where role_id = #{roleId}")
    void deleteByRoleId(Long roleId);
}
