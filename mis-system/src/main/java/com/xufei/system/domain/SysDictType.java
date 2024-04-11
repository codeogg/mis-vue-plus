package com.xufei.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xufei.common.core.BaseEntity;
import lombok.Data;

@Data
@TableName(value = "sys_dict_typ")
public class SysDictType extends BaseEntity {

    private Long dictId;
    private String dictName;
    private String dictType;
    private String status;
    private String remark;

}
