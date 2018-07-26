package com.shengruitn.dao;

import com.shengruitn.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderDtailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void findByOrderId() {
        List<OrderDetail> orderDetailList = repository.findByOrderId("1234567");
        Assert.assertNotEquals(0,orderDetailList.size());
    }
}