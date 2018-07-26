package com.shengruitn.service.impl;

import com.shengruitn.dataobject.OrderMaster;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class OrderMasterServiceImplTest {

    @Autowired
    private OrderMasterServiceImpl orderMasterService;

    @Test
    public void save() {

    }

    @Test
    public void findOne() {
        OrderMaster orderMaster = orderMasterService.findOne("1234567");
        System.out.println(orderMaster.toString());
    }

    @Test
    public void findByBuyerName() {
        List<OrderMaster> orderMasters = orderMasterService.findByBuyerName("tangah");
        Assert.assertNotEquals(0,orderMasters.size());
    }

    @Test
    public void findByBuyerPhone() {
        List<OrderMaster> orderMasters = orderMasterService.findByBuyerPhone("12131423431");
        Assert.assertNotEquals(0,orderMasters.size());
    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest request = new PageRequest(0,3);
        Page<OrderMaster> orderMasters = orderMasterService.findByBuyerOpenid("abc123123", request);
        Assert.assertNotEquals(0,orderMasters.getSize());
    }
}