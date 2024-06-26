package com.xufei.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xufei.common.core.TreeEntity;
import lombok.Data;

@Data
@TableName(value = "sys_menu")
public class SysMenu extends TreeEntity<SysMenu> {

    private String menuName;
    private String menuType;
    private String path;
    private String perms;
    private Integer sortNum;
    private String todoSql;
    private String icon;
    private String component;
    private Integer visible;
    private Integer keepAlive;
    private Long siteId;

}
