package com.hualala.conference.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseResponse<T> implements Serializable {

    private String code;
    private String msg;
    private T data;

    public BaseResponse(String code, T data) {
        this.code = code;
        this.data = data;
        this.msg = "执行成功";
    }

    public BaseResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResponse(String code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }
}