package com.shengruitn.form;

/*
 *   订单表单验证
 *   @author tangah
 *   @create 2018-06-28 20:56
 */

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class OrderForm {

    /* 买家姓名 */
    @NotEmpty(message = "姓名必填")
    private String name;

    /* 买家手机号 */
    @NotEmpty(message = "手机号必填")
    private String phone;

    /* 买家地址 */
    @NotEmpty(message = "地址必填")
    private String address;

    /* 买家微信openid */
    @NotEmpty(message = "openid必填")
    private String openid;

    /* 购物车列表 */
    @NotEmpty(message = "购物车不能为空")
    private String items;

}
