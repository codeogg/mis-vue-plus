package com.xufei.system.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.xufei.common.excel.converter.DeptConverter;
import com.xufei.common.excel.converter.StatusConverter;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysUserExportVo implements Serializable {

    @ExcelProperty("账号")
    private String userName;

    @ExcelProperty("姓名")
    private String nickName;

    @ExcelProperty("工号")
    private String jobNumber;

    @ExcelProperty(value = "部门", converter = DeptConverter.class)
    private Long deptId;

    @ExcelProperty("邮箱")
    private String email;

    @ExcelProperty("性别")
    private String sex;

    @ExcelProperty("上次登录IP")
    private String loginIp;

    @ExcelProperty("上次登录日期")
    private Date lastLoginTime;

    @ExcelProperty(value = "状态", converter = StatusConverter.class)
    private Integer status;
}
