package com.shengruitn.dto;

/*
 *   订单传输对象
 *   @author tangah
 *   @create 2018-06-27 12:53
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.shengruitn.Enum.OrderStatusEnum;
import com.shengruitn.Enum.PayStatusEnum;
import com.shengruitn.dataobject.OrderDetail;
import com.shengruitn.utils.EnumUtil;
import com.shengruitn.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
//@JsonSerialize (include = JsonSerialize.Inclusion.NON_NULL)
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    // 订单号
    private String orderId;

    // 买家姓名
    private String buyerName;

    // 买家手机
    private String buyerPhone;

    // 买家地址
    private String buyerAddress;

    // 买家微信openId
    private String buyerOpenid;

    // 订单金额
    private BigDecimal orderAmount;

    // 订单状态
    private Integer orderStatus;

    // 支付状态
    private Integer payStatus;

    // 创建时间
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    // 更新时间
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    // 订单详情
    private List<OrderDetail> orderDetailList;

    @JsonIgnore
    public OrderStatusEnum getOderStatusEnum() {
        return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum() {
        return EnumUtil.getByCode(payStatus, PayStatusEnum.class);
    }
}
