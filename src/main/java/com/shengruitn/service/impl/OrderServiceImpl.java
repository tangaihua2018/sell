package com.shengruitn.service.impl;

/*
 *   订单操作实现
 *   @author tangah
 *   @create 2018-06-27 13:02
 */

import com.shengruitn.Enum.OrderStatusEnum;
import com.shengruitn.Enum.PayStatusEnum;
import com.shengruitn.Enum.ResultEnum;
import com.shengruitn.converter.OderMaster2OrderDTOConverter;
import com.shengruitn.dao.OrderDetailRepository;
import com.shengruitn.dao.OrderMasterRepository;
import com.shengruitn.dataobject.OrderDetail;
import com.shengruitn.dataobject.OrderMaster;
import com.shengruitn.dataobject.ProductInfo;
import com.shengruitn.dto.CartDTO;
import com.shengruitn.dto.OrderDTO;
import com.shengruitn.exception.SellException;
import com.shengruitn.service.*;
import com.shengruitn.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterService orderMasterService;

    @Autowired
    private PayService payService;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private WebSocket webSocket;
    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(0);

//        List<CartDTO> cartDTOList = new ArrayList<>();

        /* 1、查询商品(数量、价格) */
        for (OrderDetail orderDetail: orderDTO.getOrderDetailList()){
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            if (productInfo == null)
            {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            /* 2\ 计算订单总价 */
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);

            /* 订单详情入库 */
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetailRepository.save(orderDetail);

//            /* 加入购物车 */
//            CartDTO cartDTO = new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity());
//            cartDTOList.add(cartDTO);
        }

        /* 3\ 写入订单数据库 */
        OrderMaster orderMaster = new OrderMaster();

        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterService.save(orderMaster);

        /* 4\ 扣库存 */
        List<CartDTO> cartDTOList =
        orderDTO.getOrderDetailList().stream().map(
                e -> new CartDTO
                        (e.getProductId(), e.getProductQuantity())
                ).collect(Collectors.toList());
        productInfoService.dereaseStock(cartDTOList);

        webSocket.sendMessage("有新的订单");

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {

        OrderMaster orderMaster = orderMasterService.findOne(orderId);
        if (orderMaster == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXISTS);
        }

        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (orderDetailList.isEmpty()){
            throw new SellException(ResultEnum.ORDER_DESC_NOT_EXISTS);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties( orderMaster, orderDTO );
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasters = orderMasterService
                .findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderDTO> orderDTOList = OderMaster2OrderDTOConverter
                .convert(orderMasters.getContent());
        Page<OrderDTO> orderDTOPage = new PageImpl<>(
                orderDTOList, pageable, orderMasters.getTotalElements());
        return orderDTOPage;
    }

    @Override
    @Transactional
    /* 这里的逻辑有待研究，先看整体逻辑的设计，后面再做修改 TODO */
    public OrderDTO cancel(OrderDTO orderDTO) {
        // 判断订单状态
        OrderMaster orderMaster = orderMasterService.findOne(orderDTO.getOrderId());
        if ( orderMaster.getOrderStatus() != OrderStatusEnum.NEW.getCode() ){
            throw new SellException(ResultEnum.ORADESTATUS_ERROR);
        }
        BeanUtils.copyProperties(orderMaster, orderDTO);

        // 修改订单状态
        orderMaster.setOrderStatus(OrderStatusEnum.CACEL.getCode());
        OrderMaster updateResult = orderMasterService.save(orderMaster);
        if ( updateResult == null )
        {
            log.error("订单取消，更新失败,orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.UPDATE_ORDER_ERROER);

        }

        // 返还库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList()))
        {
            log.error("[取消订单] 更新失败，订单无详情");
            throw new SellException(ResultEnum.UPDATE_ORDER_ERROER);
        }
        List<CartDTO> cartDTOList = new ArrayList<>();
        for (OrderDetail orderDetail: orderDTO.getOrderDetailList()){
            CartDTO cartDTO = new CartDTO(orderDetail.getProductId(),
                    orderDetail.getProductQuantity());
            cartDTOList.add(cartDTO);
        }
        productInfoService.increaseStock(cartDTOList);

        // 如果支付成功，要退款
        if (orderMaster.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())){
            payService.refund(orderDTO);
        }

        webSocket.sendMessage("取消订单");
        orderDTO.setOrderStatus(OrderStatusEnum.CACEL.getCode());
        return orderDTO;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        // 判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("[完结订单] 订单状态不正确，oraceId={}, orderStatus={}",
                    orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORADESTATUS_ERROR);
        }

        // 修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMasterService.save(orderMaster);

        return orderDTO;
    }


    @Override
    @Transactional
    public OrderDTO paied(OrderDTO orderDTO) {
        // 判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("[支付订单] 订单状态不正确，oraceId={}, orderStatus={}",
                    orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORADESTATUS_ERROR);
        }

        // 判断支付状态
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.error("[完结订单] 支付状态不正确，oraceId={}, payStatus={}",
                    orderDTO.getOrderId(), orderDTO.getPayStatus());
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }

        // 修改支付状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMasterService.save(orderMaster);

        return orderDTO;
    }


    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        Page<OrderMaster> orderMasters = orderMasterRepository.findAll(pageable);
        List<OrderDTO> orderDTOList = OderMaster2OrderDTOConverter
                .convert(orderMasters.getContent());
        Page<OrderDTO> orderDTOPage = new PageImpl<>(
                orderDTOList, pageable, orderMasters.getTotalElements());
        return orderDTOPage;
    }
}
