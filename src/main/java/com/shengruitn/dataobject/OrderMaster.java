package com.shengruitn.dataobject;/*
 *   买家订单
 *   @author tangah
 *   @create 2018-06-25 22:58
 */

import com.shengruitn.Enum.OrderStatusEnum;
import com.shengruitn.Enum.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@DynamicUpdate
@Data
public class OrderMaster {

    @Id
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
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    // 支付状态
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    // 创建时间
    private Date createTime;

    // 更新时间
    private Date updateTime;

}
