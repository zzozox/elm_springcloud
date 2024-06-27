package com.example.business_provider_13003.controller;

import com.example.business_provider_13003.service.BusinessService;
import com.example.common.entity.RestBean;
import com.example.common.entity.dto.Business;
import com.example.common.entity.vo.request.business.BusinessVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
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
 * Business相关Controller
 */
@RestController
@RequestMapping("/business")
@Tag(name = "business", description = "business相关Controller")
public class BusinessController {
    @Resource
    private BusinessService businessService;

    /**
     * 列出所有商家
     */
    @GetMapping("/list")
    public RestBean<List<Business>> getAllBusinesses() {
        List<Business> businesses=businessService.getAllBusinesses();
        return RestBean.success(businesses);
    }

    /**
     * 根据商家类型id查找商家
     * @param orderTypeId
     * @return
     */
    @PostMapping("/byOrderTypeId/{orderTypeId}")
    public RestBean<List<Business>> findBusinessesByOrderTypeId(@PathVariable Integer orderTypeId) {
        List<Business> businesses = businessService.listBusinessesByOrderTypeId(orderTypeId);
        return RestBean.success(businesses);
    }

    /**
     * 根据商家id查询商家信息
     * @param businessId
     * @return
     */
    @PostMapping("/byBusinessId/{businessId}")
    public RestBean<Business> findBusinessById(@PathVariable Integer businessId) {
        try {
            Business business = businessService.getBusinessById(businessId);
            if (business != null) {
                return RestBean.success(business);
            } else {
                return RestBean.failure(400,"无该商家");
            }
        } catch (Exception e) {
            return RestBean.failure(500, "服务器错误");
        }
    }

    /**
     * 新建business
     * @param businessVo
     * @return
     */
    @PostMapping("/createBusiness")
    public RestBean<Void> createBusiness(@RequestBody BusinessVo businessVo) {
        return this.messageHandle(() ->businessService.createBusiness(businessVo));
    }

    /**
     * 软删除business
     * @param businessId
     * @return
     */
    @PostMapping("/deleteBusienss/{businessId}")
    public RestBean<Void> deleteBusienss(@PathVariable Integer businessId) {
        return this.messageHandle(() ->businessService.deleteBusienss(businessId));

    }


    /**
     * 修改商家信息
     * @param business
     * @return
     */
    @PostMapping("/updateBusiness")
    public RestBean<Void> updateBusiness(@RequestBody Business business) {
       return this.messageHandle(() ->businessService.updateBusiness(business));
    }

    private <T> RestBean<T> messageHandle(Supplier<String> action){
        String message = action.get();
        if(message == null)
            return RestBean.success();
        else
            return RestBean.failure(400, message);
    }
}


