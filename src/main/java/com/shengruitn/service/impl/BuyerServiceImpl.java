package com.shengruitn.service.impl;
/*
 *   买家
 *   @author tangah
 *   @create 2018-06-29 1:33
 */

import com.shengruitn.Enum.ResultEnum;
import com.shengruitn.dto.OrderDTO;
import com.shengruitn.exception.SellException;
import com.shengruitn.service.BuyerService;
import com.shengruitn.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if (orderDTO == null){
            log.error("【取消订单】查询不到该订单，orderId={},orderDTO={}",
                    orderId, orderDTO );
            throw new SellException(ResultEnum.ORDER_NOT_EXISTS);
        }
        orderService.cancel(orderDTO);
        return orderDTO;
    }

    private OrderDTO checkOrderOwner(String openid, String orderId){
        OrderDTO orderDTO = orderService.findOne(orderId);
        if ( orderDTO == null ){
            return null;
        }
        if ( !orderDTO.getBuyerOpenid().equals(openid) ){
            log.error("【查询订单】订单的openid不一致，openid=[{}],orderDTO=[{}]",
                    openid, orderDTO );
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
