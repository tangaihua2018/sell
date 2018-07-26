package com.shengruitn.service.impl;

import com.shengruitn.Enum.OrderStatusEnum;
import com.shengruitn.Enum.PayStatusEnum;
import com.shengruitn.dao.OrderDetailRepository;
import com.shengruitn.dao.OrderDtailRepositoryTest;
import com.shengruitn.dataobject.OrderDetail;
import com.shengruitn.dto.OrderDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("tangah");
        orderDTO.setBuyerPhone("123123213");
        orderDTO.setBuyerOpenid("abc123123");
        orderDTO.setBuyerAddress("广州市");

        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductQuantity(1);
        o1.setProductId("1");
        orderDetailList.add(o1);

        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO result = orderService.create(orderDTO);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() {
        OrderDTO orderDTO = orderService.findOne("1530168360790143205");
        System.out.println(orderDTO.toString());
    }

    @Test
    public void findList() {
        PageRequest request = new PageRequest(0,3 );
        Page<OrderDTO> orderDTOPage = orderService.findList("abc123123", request );
        System.out.println(orderDTOPage.toString());
    }

    @Test
    public void cancel() {
        OrderDTO dto = new OrderDTO();
        dto.setOrderId("1530168360790143205");
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId("1530168360790143205");
        dto.setOrderDetailList(orderDetailList);
        OrderDTO result = orderService.cancel(dto);
        Assert.assertEquals(OrderStatusEnum.CACEL.getCode(), result.getOrderStatus());
    }

    @Test
    public void finish() {
        OrderDTO orderDTO = orderService.findOne("1530168360790143205");
        orderService.finish(orderDTO);
        Assert.assertEquals(orderDTO.getOrderStatus(), OrderStatusEnum.FINISHED.getCode());
    }

    @Test
    public void paied() {
        OrderDTO orderDTO = orderService.findOne("1530168360790143205");
        orderService.paied(orderDTO);
        Assert.assertEquals(orderDTO.getPayStatus(), PayStatusEnum.SUCCESS.getCode());
    }

    @Test
    public void findListM(){
        PageRequest request = new PageRequest(1,10);
        Page<OrderDTO> orderDTOPage = orderService.findList( request );
        //System.out.println(orderDTOPage.toString());
        Assert.assertTrue("查询所有列别",orderDTOPage.getTotalElements()<0);
    }
}