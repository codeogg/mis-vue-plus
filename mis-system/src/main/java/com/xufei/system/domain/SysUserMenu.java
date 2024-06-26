package com.xufei.system.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xufei.common.core.BaseEntity;
import lombok.Data;

@Data
@TableName(value = "sys_user_menu")
public class SysUserMenu extends BaseEntity {

    private Long userId;
    private Long menuId;
    private Integer type;
    private Long siteId;

    @TableField(exist = false)
    private String perms;

}
