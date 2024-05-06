package com.xufei.system.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xufei.common.core.BaseEntity;
import lombok.Data;

@Data
@TableName(value = "sys_company")
public class SysCompany extends BaseEntity {

    private Long deptId;
    private String fullName;
    private String tin;
    private String address;
    private String telephone;
    private String openingBank;
    private String bankAccount;
    private String location;
    private String shortName;

    @TableField(exist = false)
    private String status;
}
