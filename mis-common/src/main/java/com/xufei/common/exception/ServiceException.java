package com.xufei.common.exception;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException{

    private Integer code;
    private String message;

    public ServiceException() {
    }

    public ServiceException(String message) {
        this.message = message;
    }

    public ServiceException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
