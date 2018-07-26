package com.shengruitn.converter;

/*
 *   主订单对象转传输对象
 *   @author tangah
 *   @create 2018-06-28 15:20
 */

import com.shengruitn.dataobject.OrderMaster;
import com.shengruitn.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class OderMaster2OrderDTOConverter {

    public static OrderDTO convert(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return  orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList){
        return  orderMasterList.stream().map( e -> convert(e) )
                .collect(Collectors.toList());
    }
}
