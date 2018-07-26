package com.shengruitn.dao;/*
 *   订单详情
 *   @author tangah
 *   @create 2018-06-27 11:07
 */

import com.shengruitn.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
    List<OrderDetail> findByOrderId(String orderId );
}
