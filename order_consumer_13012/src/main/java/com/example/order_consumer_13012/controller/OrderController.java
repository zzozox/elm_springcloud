package com.example.order_consumer_13012.controller;

import com.example.order_consumer_13012.controller.apiClient.OrderApiClient;
import com.example.common.entity.RestBean;
import com.example.common.entity.dto.Orders;
import com.example.common.entity.vo.request.order.OrderVo;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Resource
    private OrderApiClient orderApiClient;

    @PostMapping("/createOrders")
    public RestBean<Void> createOrders(@RequestBody @Valid OrderVo vo) {
        return orderApiClient.createOrders(vo);
    }

    @PostMapping("/getOrdersById/{orderId}")
    public RestBean<Orders> getOrdersById(@PathVariable Integer orderId) {
        return orderApiClient.getOrdersById(orderId);
    }

    @PostMapping("/listOrdersByUserId/{userId}")
    public RestBean<List<Orders>> listOrdersByUserId(@PathVariable Integer userId) {
        return orderApiClient.listOrdersByUserId(userId);
    }
}
