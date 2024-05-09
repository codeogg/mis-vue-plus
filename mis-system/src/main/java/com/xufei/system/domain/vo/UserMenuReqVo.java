package com.xufei.system.domain.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserMenuReqVo implements Serializable {

    private Long siteId;
    private Long userId;

}
