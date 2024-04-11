package com.xufei.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xufei.common.core.TreeEntity;
import lombok.Data;

@Data
@TableName(value = "sys_menu")
public class SysMenu extends TreeEntity<SysMenu> {

    private Long menuId;
    private String menuName;
    private Integer orderNum;
    private String path;
    private String component;
    private String queryParam;
    private String isFrame;
    private String isCache;
    private String menuType;
    private String visible;
    private String status;
    private String perms;
    private String icon;
    private String remark;
}
