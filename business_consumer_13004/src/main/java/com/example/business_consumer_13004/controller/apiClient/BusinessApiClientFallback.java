package com.example.business_consumer_13004.controller.apiClient;

import com.example.common.entity.RestBean;
import com.example.common.entity.dto.Business;
import com.example.common.entity.vo.request.business.BusinessVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BusinessApiClientFallback implements BusinessApiClient {

    @Override
    public RestBean<List<Business>> getAllBusinesses() {
        return RestBean.failure(500, "服务不可用，无法获取商家列表");
    }

    @Override
    public RestBean<List<Business>> findBusinessesByOrderTypeId(Integer orderTypeId) {
        return RestBean.failure(500, "服务不可用，无法根据订单类型ID查找商家");
    }

    @Override
    public RestBean<Business> findBusinessById(Integer businessId) {
        return RestBean.failure(500, "服务不可用，无法根据商家ID查询商家信息");
    }

    @Override
    public RestBean<Void> createBusiness(BusinessVo businessVo) {
        return RestBean.failure(500, "服务不可用，无法创建商家");
    }

    @Override
    public RestBean<Void> deleteBusienss(Integer businessId) {
        return RestBean.failure(500, "服务不可用，无法删除商家");
    }

    @Override
    public RestBean<Void> updateBusiness(Business business) {
        return RestBean.failure(500, "服务不可用，无法更新商家信息");
    }
}
