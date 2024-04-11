package com.xufei.system.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "sys_role_dept")
public class SysRoleDept implements Serializable {

    private Long roleId;
    private Long deptId;
}
