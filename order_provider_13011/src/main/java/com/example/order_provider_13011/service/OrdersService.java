package com.example.order_provider_13011.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.entity.dto.Orders;
import com.example.common.entity.vo.request.order.OrderVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author root
 * @since 2023-09-21
 */
public interface OrdersService extends IService<Orders> {

    Orders createOrders(OrderVo vo);
    Orders getOrdersById(Integer orderId);
    List<Orders> listOrdersByUserId(Integer userId);
    String payOrder(Integer orderId);

}
