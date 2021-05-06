package com.example.mall.admin.controller;


import com.example.mall.admin.entity.OmsOrder;
import com.example.mall.admin.service.OmsOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author D_Richard
 * @since 2021-04-05
 */
@Controller
@RequestMapping("/order")
public class OmsOrderController {
    @Autowired
    private OmsOrderService orderService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    private List<OmsOrder> ListByUserid(){
        List<OmsOrder> list = orderService.list();
        return list;
    }

}
