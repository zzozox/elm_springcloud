package com.example.orderdetailet_provider_13013.service;

import com.example.common.entity.dto.OrderDetailet;

import java.util.List;

public interface OrderDetailetService {
    List<OrderDetailet> getOrderDetailetByOrderId(Integer orderId);
}
