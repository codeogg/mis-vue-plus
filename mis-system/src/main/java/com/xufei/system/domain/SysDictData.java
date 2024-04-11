package com.xufei.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xufei.common.core.BaseEntity;
import lombok.Data;

@Data
@TableName(value = "sys_dict_data")
public class SysDictData extends BaseEntity {

    private Long dictCode;
    private Integer dictSort;
    private String dictLabel;
    private String dictValue;
    private String dictType;
    private String cssClass;
    protected String listClass;
    private String isDefault;
    private String status;
    private String remark;

}
