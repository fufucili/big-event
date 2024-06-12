package com.big.exception;

import com.big.enums.AppHttpCodeEnum;

/**
 * 统一异常处理
 *
 * @author TuYongbin
 * @Date 2023/11/22 15:41
 */
public class ServiceException extends RuntimeException{
    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public ServiceException(AppHttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getMsg());
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();
    }

    public ServiceException(String msg) {
        this.code = AppHttpCodeEnum.SYSTEM_ERROR.getCode();
        this.msg = msg;
    }
}
