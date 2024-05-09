package com.xufei.system.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AssignUserRoleDto implements Serializable {

    private Long userId;
    private List<Long> roleIds;
}
