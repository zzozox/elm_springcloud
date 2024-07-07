package com.example.orderdetailet_provider_13013.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.entity.dto.OrderDetailet;
import com.example.orderdetailet_provider_13013.mapper.OrderDetailetMapper;
import com.example.orderdetailet_provider_13013.service.OrderDetailetService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailetServiceImpl implements OrderDetailetService {
    @Resource
    OrderDetailetMapper orderDetailetMapper;

    @Override
    public List<OrderDetailet> getOrderDetailetByOrderId(Integer orderId) {
        QueryWrapper<OrderDetailet> orderDetailetQueryWrapper=new QueryWrapper<>();
        orderDetailetQueryWrapper
                .eq("orderId",orderId);
        return orderDetailetMapper.selectList(orderDetailetQueryWrapper);
    }
}
