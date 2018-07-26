package com.shengruitn.Enum;/*
 *   商品上架状态
 *   @author tangah
 *   @create 2018-06-26 10:53
 */

import lombok.Data;
import lombok.Getter;

@Getter
public enum ProductStatusEnum implements CodeEnum{
    UP(0, "在架"),
    DOWN(1, "下架");


    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
