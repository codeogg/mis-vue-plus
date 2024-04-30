package com.xufei.system.domain;


import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xufei.common.constants.CommonConstants;
import com.xufei.common.core.BaseEntity;
import com.xufei.common.excel.converter.DeptConverter;
import com.xufei.common.excel.converter.StatusConverter;
import lombok.Data;
import lombok.ToString;

import java.util.Arrays;
import java.util.Date;

@Data
@ToString(callSuper = true)
@TableName(value = "sys_user")
@ExcelIgnoreUnannotated
public class SysUser extends BaseEntity {

    @ExcelProperty("账号")
    private String userName;

    private String password;

    @ExcelProperty("姓名")
    private String nickName;

    @ExcelProperty("工号")
    private String jobNumber;

    @ExcelProperty(value = "部门", converter = DeptConverter.class)
    private Long deptId;

    private String email;
    private String emailPassword;

    @ExcelProperty("性别")
    private String sex;
    private String avatar;
    private String loginIp;
    private Date lastLoginTime;

    @ExcelProperty(value = "状态", converter = StatusConverter.class)
    private Integer status;

    @TableField(exist = false)
    private SysDept dept;

    public boolean isAdmin(){
        return Arrays.asList(CommonConstants.ADMIN_LIST).contains(this.userName);
    }

}
