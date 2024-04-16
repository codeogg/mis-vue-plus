package com.xufei.system.domain;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xufei.common.constants.CommonConstants;
import com.xufei.common.core.BaseEntity;
import lombok.Data;
import lombok.ToString;

import java.util.Arrays;
import java.util.Date;

@Data
@ToString(callSuper = true)
@TableName(value = "sys_user")
public class SysUser extends BaseEntity {

    private String userName;
    private String password;
    private String nickName;
    private String jobNumber;
    private Long deptId;
    private String email;
    private String emailPassword;
    private String sex;
    private String avatar;
    private String loginIp;
    private Date lastLoginTime;
    private Integer status;

    @TableField(exist = false)
    private SysDept dept;

    public boolean isAdmin(){
        return Arrays.asList(CommonConstants.ADMIN_LIST).contains(this.userName);
    }

}
