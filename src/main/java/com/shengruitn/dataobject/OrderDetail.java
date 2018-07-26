package com.shengruitn.dataobject;/*
 *   订单详情
 *   @author tangah
 *   @create 2018-06-27 10:55
 */

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class OrderDetail {

    @Id
    /* 主键 */
    private String detailId;

    /* 订单号 */
    private String orderId;

    /* 商品编号 */
    private String productId;

    /* 商品名称 */
    private String productName;

    /* 商品单价 */
    private BigDecimal productPrice;

    /* 商品数量 */
    private Integer productQuantity;

    private String productIcon;

    private Date createTime;

    private Date updateTime;
}
