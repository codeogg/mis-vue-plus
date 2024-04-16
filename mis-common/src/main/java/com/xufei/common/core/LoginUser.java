package com.xufei.common.core;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
public class LoginUser implements Serializable {


    private Long userId;
    private String userName;
    private String nickName;
    private Long deptId;
    private String deptName;
    private Long siteId;
    private String avatar;
    private Set<String> menuPermission;
    private Set<String> rolePermission;


    private String token;
    //    private String userType;
    private Date loginTime;
    private Long expireTime;
    private String ipaddr;
    private String loginLocation;
    private String browser;
    private String os;



    /*public String getLoginId() {
        if (userType == null) {
            throw new IllegalArgumentException("用户类型不能为空");
        }
        if (userId == null) {
            throw new IllegalArgumentException("用户ID不能为空");
        }
        return userType + ":" + userId;
    }*/
}
