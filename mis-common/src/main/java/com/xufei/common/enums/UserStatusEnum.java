package com.xufei.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatusEnum {

    NORMAL(0, "正常"),
    DISABLED(1, "停用");

    private Integer code;
    private String description;
}
