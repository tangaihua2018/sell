package com.shengruitn.service;
/*
 *   买家
 *   @author tangah
 *   @create 2018-06-29 1:30
 */

import com.shengruitn.dto.OrderDTO;

public interface BuyerService {
    // 查询一个订单
    OrderDTO findOrderOne( String openid, String orderId );

    // 取消订单
    OrderDTO cancelOrder( String openid, String orderId );
}
