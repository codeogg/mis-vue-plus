package com.xufei.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xufei.common.core.BaseEntity;
import lombok.Data;

@Data
@TableName("sys_site")
public class SysSite extends BaseEntity {

    private String siteName;
    private Integer status;
    private String database;
    private String website;
}
