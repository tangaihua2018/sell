package com.shengruitn.dao;

import com.shengruitn.dataobject.OrderMaster;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class orderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void findByBuyerName() {
        List<OrderMaster> orderMasters = repository.findByBuyerName("tangah");
        //System.out.println(orderMasters.toString());
        //if ( orderMasters == null )
            Assert.assertNotEquals(0, orderMasters.size() );
    }

    @Test
    public void findByBuyerPhone() {
        List<OrderMaster> orderMasters = repository.findByBuyerPhone("1111111231");
        Assert.assertNotEquals(0, orderMasters.size() );
    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest request = new PageRequest(1,3);
        Page<OrderMaster> orderMasters = repository.findByBuyerOpenid("123", request );
        Assert.assertNotEquals(0, orderMasters.getTotalPages() );
    }

    @Test
    public void findOne() {
        OrderMaster orderMaster = repository.findOne("1234567");
        System.out.println(orderMaster.toString());
    }

    @Test
    public void saveTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setBuyerAddress("佛山市");
        orderMaster.setBuyerName("tangah");
        orderMaster.setBuyerOpenid("123");
        orderMaster.setBuyerPhone("12131423431");
        orderMaster.setOrderAmount(new BigDecimal(123));
        orderMaster.setOrderId("3124124");

        repository.save(orderMaster);
    }
}