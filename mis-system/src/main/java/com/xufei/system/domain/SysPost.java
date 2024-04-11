package com.xufei.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xufei.common.core.BaseEntity;
import lombok.Data;

@Data
@TableName(value = "sys_post")
public class SysPost extends BaseEntity {

    private Long postId;
    private String postCode;
    private String postName;
    private Integer postSort;
    private String status;
    private String remark;
}
