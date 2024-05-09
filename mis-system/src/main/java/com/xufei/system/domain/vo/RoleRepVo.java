package com.xufei.system.domain.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoleRepVo implements Serializable {

    private Long roleId;
    private String roleName;
    private boolean alreadyHave;
}
