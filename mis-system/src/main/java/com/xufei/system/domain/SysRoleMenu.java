package com.xufei.system.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import com.xufei.common.core.BaseEntity;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "sys_role_menu")
public class SysRoleMenu extends BaseEntity {

    private Long roleId;
    private Long menuId;
    private Long siteId;
}
