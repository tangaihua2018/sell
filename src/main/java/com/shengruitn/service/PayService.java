package com.shengruitn.service;/*
 *   支付服务
 *   @author tangah
 *   @create 2018-07-02 16:49
 */

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import com.shengruitn.dataobject.ProductInfo;
import com.shengruitn.dto.OrderDTO;

public interface PayService {

    /* 向微信发起支付订单 */
    public PayResponse ceate(OrderDTO orderDTO);

    /* 接收微信支付结果异步通知 */
    public PayResponse notify(String notifyData);

    /* 退款 */
    RefundResponse refund(OrderDTO orderDTO);


}
