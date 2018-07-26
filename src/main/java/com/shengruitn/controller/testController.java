package com.shengruitn.controller;

import com.shengruitn.dto.OrderDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @ Author     ：tangah.
 * @ Date       ：Created in 9:46 2018/7/8
 * @ Description：test
 * @ Modified By：
 * @Version: $
 */
@Controller
@RequestMapping("/test")
public class testController {

    @RequestMapping("/ttt")
    public ModelAndView test(Map<String,Object> map) {

//        OrderDTO orderDTO = new OrderDTO();
        map.put("testVal", "hello!");
//        map.put("order",orderDTO);

        return new ModelAndView("/ttt", map);
    }

}
