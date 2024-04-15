package com.xufei.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xufei.common.core.BaseEntity;
import lombok.Data;

@Data
@TableName(value = "sys_dict_type")
public class SysDictType extends BaseEntity {

    private String dictName;
    private String dictType;
    private String remark;

}
