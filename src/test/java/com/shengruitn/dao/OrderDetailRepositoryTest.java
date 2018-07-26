package com.shengruitn.dao;

import com.shengruitn.dataobject.OrderDetail;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)

public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void findByOrderId() {
        List<OrderDetail> orderDetailList = repository.findByOrderId("1530099639576507626");
        Assert.assertNotNull(orderDetailList);
    }

    @Test
    public void saveTest() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("12345690");
        orderDetail.setOrderId("1234567");
        orderDetail.setProductIcon("http://icon.com");
        orderDetail.setProductId("3");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductQuantity(3);
        orderDetail.setProductPrice(new BigDecimal(23));
        OrderDetail result = repository.save(orderDetail);
        Assert.assertNotNull(result);
    }
}