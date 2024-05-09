package com.xufei.system.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AssignRoleMenuDto implements Serializable {

    private Long roleId;
    private List<Long> menuIds;
    private Long siteId;
}
