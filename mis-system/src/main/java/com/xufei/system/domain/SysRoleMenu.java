package com.xufei.system.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import com.xufei.common.core.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@TableName(value = "sys_role_menu")
@NoArgsConstructor
@AllArgsConstructor
public class SysRoleMenu extends BaseEntity {

    private Long roleId;
    private Long menuId;
    private Long siteId;
}
