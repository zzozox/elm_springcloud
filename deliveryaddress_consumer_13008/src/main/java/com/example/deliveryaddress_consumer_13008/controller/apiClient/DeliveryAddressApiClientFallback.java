package com.example.deliveryaddress_consumer_13008.controller.apiClient;

import com.example.common.entity.RestBean;
import com.example.common.entity.dto.DeliveryAddress;
import com.example.common.entity.vo.request.deliveryaddress.DeliveryAddressVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeliveryAddressApiClientFallback implements DeliveryAddressApiClient {

    private static final Logger logger = LoggerFactory.getLogger(DeliveryAddressApiClientFallback.class);

    @Override
    public RestBean<List<DeliveryAddress>> listDeliveryAddressByUserId(Integer userId) {
        logFallback("listDeliveryAddressByUserId", "userId: " + userId);
        return RestBean.failure(500, "服务不可用，无法列出用户收货地址");
    }

    @Override
    public RestBean<DeliveryAddress> getDeliveryAddressById(Integer daId) {
        logFallback("getDeliveryAddressById", "daId: " + daId);
        return RestBean.failure(500, "服务不可用，无法根据收货地址编号获取信息");
    }

    @Override
    public RestBean<Void> createDeliveryAddress(DeliveryAddressVo vo) {
        logFallback("createDeliveryAddress", "deliveryAddressVo: " + vo);
        return RestBean.failure(500, "服务不可用，无法创建收货地址");
    }

    @Override
    public RestBean<Void> updateDeliveryAddress(DeliveryAddress deliveryAddress) {
        logFallback("updateDeliveryAddress", "deliveryAddress: " + deliveryAddress);
        return RestBean.failure(500, "服务不可用，无法更新收货地址");
    }

    @Override
    public RestBean<Void> deleteDeliveryAddress(Integer daId) {
        logFallback("deleteDeliveryAddress", "daId: " + daId);
        return RestBean.failure(500, "服务不可用，无法删除收货地址");
    }

    private void logFallback(String methodName, String params) {
        logger.error("Fallback triggered for method: {}. Params: {}. Possible reasons: service is down or network issue.", methodName, params);
    }
}
