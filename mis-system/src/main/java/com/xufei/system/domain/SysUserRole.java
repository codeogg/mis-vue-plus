package com.xufei.system.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xufei.common.core.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@TableName(value = "sys_user_role")
@NoArgsConstructor
public class SysUserRole extends BaseEntity {

    private Long userId;
    private Long roleId;

    public SysUserRole(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}
