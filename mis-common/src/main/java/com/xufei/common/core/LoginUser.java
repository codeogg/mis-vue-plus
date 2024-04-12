package com.xufei.common.core;

import com.xufei.common.core.dto.RoleDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class LoginUser implements Serializable {

    private Long userId;
    private Long deptId;
    private String deptName;
    private String token;
    private String userType;
    private Date loginTime;
    private Long expireTime;
    private String ipaddr;
    private String loginLocation;
    private String browser;
    private String os;
    private String username;

    private Set<String> menuPermission;
    private Set<String> rolePermission;
    private List<RoleDTO> roles;
    private Long roleId;

    public String getLoginId() {
        if (userType == null) {
            throw new IllegalArgumentException("用户类型不能为空");
        }
        if (userId == null) {
            throw new IllegalArgumentException("用户ID不能为空");
        }
        return userType + ":" + userId;
    }
}
