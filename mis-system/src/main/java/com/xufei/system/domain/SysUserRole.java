package com.xufei.system.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "sys_user_role")
public class SysUserRole implements Serializable {

    private Long userId;
    private Long roleId;

}
