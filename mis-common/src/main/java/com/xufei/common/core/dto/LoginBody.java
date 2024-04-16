package com.xufei.common.core.dto;

import lombok.Data;

@Data
public class LoginBody {

    private String username;
    private String password;
    // 要登录哪个系统的id
    private Long siteId;
}
