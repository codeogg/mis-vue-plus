package com.xufei.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xufei.common.core.TreeEntity;
import lombok.Data;

@Data
@TableName(value = "sys_dept")
public class SysDept extends TreeEntity<SysDept> {

    private String ancestors;
    private String deptName;
    private Integer sortNum;
    private String status;

}
