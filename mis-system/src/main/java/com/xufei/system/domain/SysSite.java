package com.xufei.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xufei.common.core.BaseEntity;
import lombok.Data;

@Data
@TableName(value = "sys_site")
public class SysSite extends BaseEntity {

    private String siteName;
    private Integer status;
    private String dbName;
    private String website;
}
