package com.shengruitn.service;

import com.shengruitn.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderMasterService {
    OrderMaster         save(OrderMaster orderMaster);
    OrderMaster         findOne(String orderId);
    List<OrderMaster>   findByBuyerName(String buyerName);
    List<OrderMaster>   findByBuyerPhone(String buyerPhone );
    Page<OrderMaster>   findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
