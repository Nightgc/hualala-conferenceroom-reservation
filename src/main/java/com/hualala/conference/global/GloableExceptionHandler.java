package com.hualala.conference.global;

import com.hualala.conference.dto.BaseResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 显示异常
 */
@ControllerAdvice
public class GloableExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseResponse handleException(Exception e) {
        return new BaseResponse<>("123", e.getMessage(), "系统异常");
    }

    @ExceptionHandler(GreatThree.class)
    @ResponseBody
    public BaseResponse handleException3(GreatThree e) {
        return new BaseResponse<>(e.code, e.getMessage(), "系统异常");
    }


}
