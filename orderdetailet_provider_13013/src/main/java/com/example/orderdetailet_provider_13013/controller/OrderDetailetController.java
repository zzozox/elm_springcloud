package com.example.orderdetailet_provider_13013.controller;

import com.example.common.entity.RestBean;
import com.example.common.entity.dto.OrderDetailet;
import com.example.orderdetailet_provider_13013.service.OrderDetailetService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orderdetailet")
public class OrderDetailetController {
    @Resource
    OrderDetailetService orderDetailetService;

    @PostMapping("/getOrderDetailetByOrderId/{orderId}")
    public RestBean<List<OrderDetailet>> getOrderDetailetByOrderId(@PathVariable Integer orderId){
        return RestBean.success(orderDetailetService.getOrderDetailetByOrderId(orderId));
    }
}
