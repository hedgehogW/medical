package com.example.usermanagement.adviser;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@ControllerAdvice
@ResponseBody
public class BaseExceptionHandlerAdvice {
    @ExceptionHandler(BaseException.class)
    public BaseResponse<?> baseException(BaseException e){
        return BaseResponse.error(e);
    }

    @ExceptionHandler(Exception.class)
    public BaseResponse<?> excption(Exception e){
        return BaseResponse.error(new BaseException(ErrorCode.UNKNOWN));
    }
}
