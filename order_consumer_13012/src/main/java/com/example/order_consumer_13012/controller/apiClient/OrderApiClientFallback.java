package com.example.order_consumer_13012.controller.apiClient;

import com.example.common.entity.RestBean;
import com.example.common.entity.dto.Orders;
import com.example.common.entity.vo.request.order.OrderVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderApiClientFallback implements OrderApiClient {

    private static final Logger logger = LoggerFactory.getLogger(OrderApiClientFallback.class);

    @Override
    public RestBean<Void> createOrders(OrderVo vo) {
        logFallback("createOrders", "orderVo: " + vo);
        return RestBean.failure(500, "服务不可用，无法创建订单");
    }

    @Override
    public RestBean<Orders> getOrdersById(Integer orderId) {
        logFallback("getOrdersById", "orderId: " + orderId);
        return RestBean.failure(500, "服务不可用，无法根据订单编号查询订单");
    }

    @Override
    public RestBean<List<Orders>> listOrdersByUserId(Integer userId) {
        logFallback("listOrdersByUserId", "userId: " + userId);
        return RestBean.failure(500, "服务不可用，无法根据用户ID查询用户所有订单");
    }

    private void logFallback(String methodName, String params) {
        logger.error("Fallback triggered for method: {}. Params: {}. Possible reasons: service is down or network issue.", methodName, params);
    }
}
