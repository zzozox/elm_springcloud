package com.example.business_consumer_13004.controller;

import com.example.business_consumer_13004.controller.apiClient.BusinessApiClient;
import com.example.common.entity.RestBean;
import com.example.common.entity.dto.Business;
import com.example.common.entity.vo.request.business.BusinessVo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/business")
public class BusinessController {

    @Resource
    private BusinessApiClient businessApiClient;

    @GetMapping("/list")
    public RestBean<List<Business>> getAllBusinesses() {
        return businessApiClient.getAllBusinesses();
    }

    @PostMapping("/byOrderTypeId/{orderTypeId}")
    public RestBean<List<Business>> findBusinessesByOrderTypeId(@PathVariable Integer orderTypeId) {
        return businessApiClient.findBusinessesByOrderTypeId(orderTypeId);
    }

    @PostMapping("/byBusinessId/{businessId}")
    public RestBean<Business> findBusinessById(@PathVariable Integer businessId) {
        return businessApiClient.findBusinessById(businessId);
    }

    @PostMapping("/createBusiness")
    public RestBean<Void> createBusiness(@RequestBody BusinessVo businessVo) {
        return businessApiClient.createBusiness(businessVo);
    }

    @PostMapping("/deleteBusienss/{businessId}")
    public RestBean<Void> deleteBusienss(@PathVariable Integer businessId) {
        return businessApiClient.deleteBusienss(businessId);
    }

    @PostMapping("/updateBusiness")
    public RestBean<Void> updateBusiness(@RequestBody Business business) {
        return businessApiClient.updateBusiness(business);
    }
}
