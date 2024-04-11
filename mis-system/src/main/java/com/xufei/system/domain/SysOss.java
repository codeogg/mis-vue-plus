package com.xufei.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xufei.common.core.BaseEntity;
import lombok.Data;

@Data
@TableName(value = "sys_oss")
public class SysOss extends BaseEntity {

    private Long ossId;
    private String fileName;
    private String originalName;
    private String fileSuffix;
    private String url;
    private String service;
}
