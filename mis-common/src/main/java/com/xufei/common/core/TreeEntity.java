package com.xufei.common.core;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TreeEntity<T> extends BaseEntity{

    @TableField(exist = false)
    private String parentName;

    private Long parentId;

    @TableField(exist = false)
    private List<T> children = new ArrayList<>();

}
