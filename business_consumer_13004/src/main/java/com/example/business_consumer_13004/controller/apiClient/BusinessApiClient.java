package com.example.business_consumer_13004.controller.apiClient;

import com.example.common.entity.RestBean;
import com.example.common.entity.dto.Business;
import com.example.common.entity.vo.request.business.BusinessVo;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "business-provider-13003", fallback = BusinessApiClientFallback.class)
@LoadBalancerClient(name = "business-provider-13003")
public interface BusinessApiClient {

    @GetMapping("/business/list")
    RestBean<List<Business>> getAllBusinesses();

    @PostMapping("/business/byOrderTypeId/{orderTypeId}")
    RestBean<List<Business>> findBusinessesByOrderTypeId(@PathVariable Integer orderTypeId);

    @PostMapping("/business/byBusinessId/{businessId}")
    RestBean<Business> findBusinessById(@PathVariable Integer businessId);

    @PostMapping("/business/createBusiness")
    RestBean<Void> createBusiness(@RequestBody BusinessVo businessVo);

    @PostMapping("/business/deleteBusienss/{businessId}")
    RestBean<Void> deleteBusienss(@PathVariable Integer businessId);

    @PostMapping("/business/updateBusiness")
    RestBean<Void> updateBusiness(@RequestBody Business business);
}
