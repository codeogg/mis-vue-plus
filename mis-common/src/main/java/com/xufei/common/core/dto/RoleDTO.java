package com.xufei.common.core.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class RoleDTO implements Serializable {

    private Long roleId;
    private String roleName;
    private String roleKey;
    private String dataScope;

}
