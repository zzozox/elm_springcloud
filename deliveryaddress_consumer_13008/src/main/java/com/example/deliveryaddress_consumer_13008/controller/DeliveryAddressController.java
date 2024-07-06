package com.example.deliveryaddress_consumer_13008.controller;

import com.example.deliveryaddress_consumer_13008.controller.apiClient.DeliveryAddressApiClient;
import com.example.common.entity.RestBean;
import com.example.common.entity.dto.DeliveryAddress;
import com.example.common.entity.vo.request.deliveryaddress.DeliveryAddressVo;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveryaddress")
public class DeliveryAddressController {

    @Resource
    private DeliveryAddressApiClient deliveryAddressApiClient;

    @PostMapping("/listDeliveryAddressByUserId/{userId}")
    public RestBean<List<DeliveryAddress>> listDeliveryAddressByUserId(@PathVariable Integer userId) {
        return deliveryAddressApiClient.listDeliveryAddressByUserId(userId);
    }

    @PostMapping("/getDeliveryAddressById/{daId}")
    public RestBean<DeliveryAddress> getDeliveryAddressById(@PathVariable Integer daId) {
        return deliveryAddressApiClient.getDeliveryAddressById(daId);
    }

    @PostMapping("/createDeliveryAddress")
    public RestBean<Void> createDeliveryAddress(@RequestBody @Valid DeliveryAddressVo vo) {
        return deliveryAddressApiClient.createDeliveryAddress(vo);
    }

    @PostMapping("/updateDeliveryAddress")
    public RestBean<Void> updateDeliveryAddress(@RequestBody @Valid DeliveryAddress deliveryAddress) {
        return deliveryAddressApiClient.updateDeliveryAddress(deliveryAddress);
    }

    @PostMapping("/deleteDeliveryAddress/{daId}")
    public RestBean<Void> deleteDeliveryAddress(@PathVariable Integer daId) {
        return deliveryAddressApiClient.deleteDeliveryAddress(daId);
    }
}
