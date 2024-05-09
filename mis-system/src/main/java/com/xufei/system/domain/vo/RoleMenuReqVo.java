package com.xufei.system.domain.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoleMenuReqVo implements Serializable {

    private Long siteId;
    private Long roleId;
}
