package com.example.deliveryaddress_provider_13007.controller;

import com.example.common.entity.RestBean;
import com.example.common.entity.dto.DeliveryAddress;
import com.example.common.entity.vo.request.deliveryaddress.DeliveryAddressVo;
import com.example.deliveryaddress_provider_13007.service.DeliveryAddressService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author root
 * @since 2023-09-21
 */

/**
 * deliveryaddress相关Controller
 */
@RestController
@RequestMapping("/deliveryaddress")
public class DeliveryaddressController {

    @Resource
    private DeliveryAddressService deliveryAddressService;

    /**
     * 列出该用户的所有收货地址
     * @param userId
     * @return
     */
    @PostMapping ("/listDeliveryAddressByUserId/{userId}")
    public RestBean<List<DeliveryAddress>> listDeliveryAddressByUserId(@PathVariable Integer userId){
        return RestBean.success(deliveryAddressService.listDeliveryAddressByUserId(userId));
    }

    /**
     * 根据收获地址编号货物收货地址信息
     * @param daId
     * @return
     */
    @PostMapping("/getDeliveryAddressById/{daId}")
    public RestBean<DeliveryAddress> getDeliveryAddressById(@PathVariable Integer daId){
        return RestBean.success(deliveryAddressService.getDeliveryAddressById(daId));
    }

    /**
     * 新增收货地址
     * @param vo
     * @return
     */
    @PostMapping("/createDeliveryAddress")
    public RestBean<Void> createDeliveryAddress(@RequestBody @Valid DeliveryAddressVo vo){
        return this.messageHandle(() ->
                deliveryAddressService.createDeliveryAddress(vo));
    }

    /**
     * 更新现有收货地址
     * @param deliveryAddress
     * @return
     */
    @PostMapping("/updateDeliveryAddress")
    public RestBean<Void> updateDeliveryAddress(@RequestBody @Valid DeliveryAddress deliveryAddress){
        return this.messageHandle(() ->
                deliveryAddressService.updateDeliveryAddress(deliveryAddress));
    }

    /**
     * 删除已有收货地址
     * @param daId
     * @return
     */
    @PostMapping("/deleteDeliveryAddress/{daId}")
    public RestBean<Void> deleteDeliveryAddress(@PathVariable Integer daId){
        return this.messageHandle(() ->
                deliveryAddressService.deleteDeliveryAddress(daId));
    }

    private <T> RestBean<T> messageHandle(Supplier<String> action){
        String message = action.get();
        if(message == null)
            return RestBean.success();
        else
            return RestBean.failure(400, message);
    }
}

