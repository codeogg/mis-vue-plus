package com.xufei.system.domain;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xufei.common.core.BaseEntity;
import lombok.Data;

@Data
@TableName(value = "sys_role")
public class SysRole extends BaseEntity {

    private Long roleId;
    private String roleName;
    private String roleKey;
    private Integer roleSort;
    private String dataScope;
    private Boolean menuCheckStrictly;
    private Boolean deptCheckStrictly;
    private String status;

    @TableLogic
    private String delFlag;

    private String remark;

    @TableField(exist = false)
    private Long[] menuIds;

    @TableField(exist = false)
    private Long[] deptIds;
}
