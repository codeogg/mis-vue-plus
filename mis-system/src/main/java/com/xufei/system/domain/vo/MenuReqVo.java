package com.xufei.system.domain.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class MenuReqVo implements Serializable {

    private Long siteId;
    private Long roleId;
    private Long userId;

}
