package com.xufei.system.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xufei.common.core.BaseEntity;
import com.xufei.common.core.TreeEntity;
import lombok.Data;

@Data
@TableName("sys_dept")
public class SysDept extends TreeEntity<SysDept> {

    private Long deptId;
    private String ancestors;
    private String deptName;
    private Integer orderNum;
    private String leader;
    private String phone;
    private String email;
    private String status;

    @TableLogic
    private String delFlag;
}
