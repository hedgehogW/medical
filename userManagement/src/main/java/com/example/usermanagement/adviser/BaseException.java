package com.example.usermanagement.adviser;

import lombok.Getter;


@Getter
// 定义自定义异常类
public class BaseException extends RuntimeException {

    private Integer code;
    private String msg;

    public BaseException() {
        super();
    }

    public BaseException(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public BaseException(ErrorCode errorCode, String msg) {
        this.code = errorCode.getCode();
        this.msg = msg;
    }

}
