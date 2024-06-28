package com.example.order_consumer_13012.controller.apiClient;

import com.example.common.entity.RestBean;
import com.example.common.entity.dto.Orders;
import com.example.common.entity.vo.request.order.OrderVo;
import jakarta.validation.Valid;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "order-provider-13011", fallback = OrderApiClientFallback.class)
@LoadBalancerClient(name = "order-provider-13011")
public interface OrderApiClient {

    @PostMapping("/orders/createOrders")
    RestBean<Void> createOrders(@RequestBody @Valid OrderVo vo);

    @PostMapping("/orders/getOrdersById/{orderId}")
    RestBean<Orders> getOrdersById(@PathVariable Integer orderId);

    @PostMapping("/orders/listOrdersByUserId/{userId}")
    RestBean<List<Orders>> listOrdersByUserId(@PathVariable Integer userId);
}
