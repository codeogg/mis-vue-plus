package com.xufei.system.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xufei.common.core.BaseEntity;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "sys_user_role")
public class SysUserRole extends BaseEntity {

    private Long userId;
    private Long roleId;
    private Long siteId;

}
