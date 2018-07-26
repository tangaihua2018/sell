package com.shengruitn.controller;/*
 *   买家订单操作
 *   @author tangah
 *   @create 2018-06-28 20:48
 */

import com.shengruitn.Enum.ResultEnum;
import com.shengruitn.VO.ResultVO;
import com.shengruitn.converter.OrderForm2OrderDTOConverter;
import com.shengruitn.dto.OrderDTO;
import com.shengruitn.exception.SellException;
import com.shengruitn.form.OrderForm;
import com.shengruitn.service.BuyerService;
import com.shengruitn.service.OrderService;
import com.shengruitn.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm,
                                               BindingResult bindingResult) {
        //orderForm.setOpenid("123123123fsafda");

        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确,orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROER.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        //下单
        OrderDTO createResult = orderService.create(orderDTO);

        Map<String,String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());

        return ResultVOUtil.success(map);
    }

    //订单列表
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value="page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size){
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】 openid为空");
            throw new SellException(ResultEnum.PARAM_ERROER);
        }
        PageRequest request = new PageRequest(page,size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid, request);
        return ResultVOUtil.success(orderDTOPage.getContent());
    }

    //订单详情
    @GetMapping("/detail")
    public ResultVO<List<OrderDTO>> detail(@RequestParam("openid") String openid,
                                         @RequestParam("orderId") String orderId){
        //参数检查
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】 openid为空");
            throw new SellException(ResultEnum.PARAM_ERROER);
        }
        if (StringUtils.isEmpty(orderId)) {
            log.error("【查询订单列表】 orderid为空");
            throw new SellException(ResultEnum.PARAM_ERROER);
        }

        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);

        return ResultVOUtil.success(orderDTO);
    }

    //取消订单
    @PostMapping("/cancel")
    public ResultVO<List<OrderDTO>> cancel(@RequestParam("openid") String openid,
                                           @RequestParam("orderId") String orderId){
        //参数检查
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】 openid为空");
            throw new SellException(ResultEnum.PARAM_ERROER);
        }
        if (StringUtils.isEmpty(orderId)) {
            log.error("【查询订单列表】 orderid为空");
            throw new SellException(ResultEnum.PARAM_ERROER);
        }

        OrderDTO orderDTO = buyerService.cancelOrder(openid, orderId);
        return ResultVOUtil.success();
    }

}
