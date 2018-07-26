package com.shengruitn.service.impl;/*
 *   买家订单主表
 *   @author tangah
 *   @create 2018-06-26 0:01
 */

import com.shengruitn.dao.OrderMasterRepository;
import com.shengruitn.dataobject.OrderMaster;
import com.shengruitn.service.OrderMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderMasterServiceImpl implements OrderMasterService {

    @Autowired
    private OrderMasterRepository repository;

    @Override
    public OrderMaster save(OrderMaster orderMaster) {
        return repository.save(orderMaster);
    }

    @Override
    public OrderMaster findOne(String orderId) {
        return repository.findOne(orderId);
    }

    @Override
    public List<OrderMaster> findByBuyerName(String buyerName) {
        return repository.findByBuyerName(buyerName);
    }

    @Override
    public List<OrderMaster> findByBuyerPhone(String buyerPhone) {
        return repository.findByBuyerPhone(buyerPhone);
    }


    @Override
    public Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = repository.findByBuyerOpenid(buyerOpenid, pageable);
        return orderMasterPage;
    }
}
