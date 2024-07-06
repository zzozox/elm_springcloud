package com.example.deliveryaddress_consumer_13008.controller.apiClient;

import com.example.common.entity.RestBean;
import com.example.common.entity.dto.DeliveryAddress;
import com.example.common.entity.vo.request.deliveryaddress.DeliveryAddressVo;
import jakarta.validation.Valid;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "deliveryaddress-provider-13007", fallback = DeliveryAddressApiClientFallback.class)
@LoadBalancerClient(name = "deliveryaddress-provider-13007")
public interface DeliveryAddressApiClient {

    @PostMapping("/deliveryaddress/listDeliveryAddressByUserId/{userId}")
    RestBean<List<DeliveryAddress>> listDeliveryAddressByUserId(@PathVariable Integer userId);

    @PostMapping("/deliveryaddress/getDeliveryAddressById/{daId}")
    RestBean<DeliveryAddress> getDeliveryAddressById(@PathVariable Integer daId);

    @PostMapping("/deliveryaddress/createDeliveryAddress")
    RestBean<Void> createDeliveryAddress(@RequestBody @Valid DeliveryAddressVo vo);

    @PostMapping("/deliveryaddress/updateDeliveryAddress")
    RestBean<Void> updateDeliveryAddress(@RequestBody @Valid DeliveryAddress deliveryAddress);

    @PostMapping("/deliveryaddress/deleteDeliveryAddress/{daId}")
    RestBean<Void> deleteDeliveryAddress(@PathVariable Integer daId);
}
