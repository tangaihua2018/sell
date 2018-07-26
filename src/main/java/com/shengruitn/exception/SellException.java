package com.shengruitn.exception;/*
 *   系统异常管理
 *   @author tangah
 *   @create 2018-06-27 15:37
 */

import com.shengruitn.Enum.ResultEnum;

public class SellException extends RuntimeException{

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
