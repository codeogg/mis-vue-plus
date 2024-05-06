package com.xufei.system.domain;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xufei.common.core.BaseEntity;
import lombok.Data;

@Data
@TableName(value = "sys_role")
public class SysRole extends BaseEntity {

    private String roleName;
    private String roleCode;
    private Integer dataScope;
    private String dataScopeDeptIds;
    private Long siteId;
}
