package com.example.usermanagement.service.impl;

import com.example.usermanagement.adviser.BaseException;
import com.example.usermanagement.adviser.ErrorCode;
import org.springframework.stereotype.Service;

@Service
public class ExceptionService {

    public void serverError() {
        try {
            Class.forName("com.mysql.jdbc.xxx.Driver");
        } catch (Exception e) {
            throw new BaseException(ErrorCode.SERVER_ERROR, "数据库驱动加载异常，出现ClassNotFoundException，请联系管理员");
        }
    }

    public void badRequest() {
        throw new BaseException(ErrorCode.BAD_REQUEST, "您输入的数据不符合业务逻辑，请确认后重新输入!");
    }

}
