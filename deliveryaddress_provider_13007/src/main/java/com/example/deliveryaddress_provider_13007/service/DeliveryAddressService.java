package com.example.deliveryaddress_provider_13007.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.entity.dto.DeliveryAddress;
import com.example.common.entity.vo.request.deliveryaddress.DeliveryAddressVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author root
 * @since 2023-09-21
 */
public interface DeliveryAddressService extends IService<DeliveryAddress> {
    List<DeliveryAddress> listDeliveryAddressByUserId(Integer userId);
    DeliveryAddress getDeliveryAddressById(Integer daId);
    String createDeliveryAddress(DeliveryAddressVo vo);
    String updateDeliveryAddress(DeliveryAddress deliveryAddress);
    String deleteDeliveryAddress(Integer daId);

}
