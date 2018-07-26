package com.shengruitn.service.impl;

import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import com.shengruitn.dto.OrderDTO;
import com.shengruitn.service.OrderService;
import com.shengruitn.service.PayService;
import com.shengruitn.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class PayServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayServiceImpl payService;

    @Test
    public void ceate() {

        OrderDTO orderDTO = orderService.findOne("1530634407841438158");

        PayResponse payRequest = payService.ceate(orderDTO);
    }

    @Test
    public void refund(){
        OrderDTO orderDTO = orderService.findOne("1530685924140815551");

        RefundResponse payResponse = payService.refund(orderDTO);

        log.info("【微信支付退款】,response={}", JsonUtil.toJson(payResponse));
    }
}