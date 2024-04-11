package com.xufei.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xufei.common.core.BaseEntity;
import lombok.Data;

@Data
@TableName(value = "sys_oss_config")
public class SysOssConfig extends BaseEntity {

    private Long ossConfigId;
    private String configKey;
    private String accessKey;
    private String secretKey;
    private String bucketName;
    private String prefix;
    private String endpoint;
    private String domain;
    private String isHttps;
    private String region;
    private String accessPolicy;
    private String status;
    private String ext1;
    private String remark;
}
