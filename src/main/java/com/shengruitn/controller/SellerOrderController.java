package com.shengruitn.controller;
/**
 *   卖家订单
 *   @author tangah
 *   @create 2018-07-04 23:59
 */

import com.shengruitn.Enum.ResultEnum;
import com.shengruitn.dto.OrderDTO;
import com.shengruitn.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    /**
     * @create by:     tangah.
     * @description:   查询订单列表
     * @create time:   2018/7/6 23:59
     * @param          [page, size, map]
     * @return         org.springframework.web.servlet.ModelAndView
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map){
        String returnUrl = "/sell/seller/order/list?page=" + page + "&size=" + size;

        PageRequest request = new PageRequest(page-1,size);
        Page<OrderDTO> orderDTOPage = orderService.findList(request);

        map.put("orderDIOPage", orderDTOPage);
        map.put("currentPage", page);
        map.put("size", size);
        map.put("retUrl", returnUrl);

        return new ModelAndView("/order/list", map);
    }

    /**
     * @create by:     tangah.
     * @description:   取消订单
     * @create time:   2018/7/6 23:58
     * @param          [ordeId, currentPage, map]
     * @return         org.springframework.web.servlet.ModelAndView
     */
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String ordeId,
                               @RequestParam(value = "returnUrl",defaultValue = "/sell/seller/order/list") String retUrl,
                               Map<String,Object> map) {
        OrderDTO orderDTO;
        try {
            orderDTO = orderService.findOne(ordeId);
            orderService.cancel(orderDTO);
        } catch (Exception e) {
            log.error("[卖家端订单取消] 查询不到订单,[{}]", e.getMessage());
            map.put("msg", e.getMessage());
            map.put("retUrl", retUrl);
            return new ModelAndView("common/error", map);
        }

        map.put("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
        map.put("retUrl", retUrl);
        return new ModelAndView("common/success");
    }

    /**
     * @create by:     tangah.
     * @description:   订单详情
     * @create time:   2018/7/7 0:03
     * @param          [ordeId, currentPage, map]
     * @return         org.springframework.web.servlet.ModelAndView
     */
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String ordeId,
                               Map<String,Object> map){
        OrderDTO orderDTO;
        String retUrl = "/sell/seller/order/detail?orderId=" + ordeId;
        try {
            orderDTO = orderService.findOne(ordeId);

        } catch (Exception e){
            log.error("[卖家端订单详情查询] 查询不到订单详情,[{}]", e.getMessage());
            map.put("msg", e.getMessage());
            map.put("retUrl", retUrl);
            return new ModelAndView("common/error", map);
        }

        map.put("orderDTO", orderDTO);
        map.put("retUrl", retUrl);
        return new ModelAndView("order/detail", map);
    }
    
    /**
     * @create by:              tangah.
     * @description:            订单完结
     * @create time:            2018/7/6 23:49
     * @param                   [ordeId, currentPage, map]
     * @return                  org.springframework.web.servlet.ModelAndView
     */
    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId") String ordeId,
                               @RequestParam(value = "returnUrl",defaultValue = "/sell/seller/order/list") String retUrl,
                               Map<String,Object> map){
        try {
            OrderDTO orderDTO = orderService.findOne(ordeId);
            orderService.finish(orderDTO);
        } catch (Exception e) {
            log.error("[卖家端完结订单] 查询不到订单,[{}]", e.getMessage());
            map.put("msg", e.getMessage());
            map.put("retUrl", retUrl);
            return new ModelAndView("common/error", map);
        }

        map.put("msg", ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
        map.put("retUrl", retUrl);
        return new ModelAndView("common/success");
    }

}
