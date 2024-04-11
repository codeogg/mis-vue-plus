package com.xufei.system.domain;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xufei.common.core.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "sys_user")
public class SysUser extends BaseEntity {

    private Long userId;
    private Long deptId;
    private String userName;
    private String nickName;
    private String userType;
    private String email;
    private String phonenumber;
    private String sex;
    private String avatar;
    private String password;
    private String status;

    @TableLogic
    private String delFlag;

    private String loginIp;
    private Date loginDate;
    private String remark;


    @TableField(exist = false)
    public SysDept dept;

    @TableField(exist = false)
    private Long[] roleIds;

    @TableField(exist = false)
    private Long[] postIds;


}
