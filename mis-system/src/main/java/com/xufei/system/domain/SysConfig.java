package com.xufei.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xufei.common.core.BaseEntity;
import lombok.Data;

@Data
@TableName("sys_config")
public class SysConfig extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long configId;
    private String configName;
    private String configKey;
    private String configValue;
    private String configType;
    private String remark;
}
