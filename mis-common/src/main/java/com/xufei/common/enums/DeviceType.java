package com.xufei.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeviceType {

    PC("pc"),
    APP("app");

    private final String device;
}
