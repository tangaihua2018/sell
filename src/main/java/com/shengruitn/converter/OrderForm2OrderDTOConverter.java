package com.shengruitn.converter;

/*
 *   订单表单转订单传输数据机构
 *   @author tangah
 *   @create 2018-06-28 22:47
 */

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shengruitn.Enum.ResultEnum;
import com.shengruitn.dataobject.OrderDetail;
import com.shengruitn.dto.OrderDTO;
import com.shengruitn.exception.SellException;
import com.shengruitn.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class OrderForm2OrderDTOConverter {
    public static OrderDTO convert(OrderForm orderForm){
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerPhone(orderForm.getPhone());

        // json列表字符串转对象列表
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        } catch (Exception e) {
            log.error("【json对象转换】错误,JSONString={}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROER);
        }

        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}
