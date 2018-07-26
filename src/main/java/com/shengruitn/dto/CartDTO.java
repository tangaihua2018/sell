package com.shengruitn.dto;

import lombok.Data;

/*
 *   购物车
 *   @author tangah
 *   @create 2018-06-27 17:48
 */
@Data
public class CartDTO {

    /* 商品代码 */
    private String productId;

    /* 购买商品数量 */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
