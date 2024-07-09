package com.example.business_provider_13003.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.entity.dto.Business;
import com.example.common.entity.vo.request.business.BusinessVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author root
 * @since 2023-09-21
 */
public interface BusinessService extends IService<Business> {
    List<Business> getAllBusinesses();
    List<Business> listBusinessesByOrderTypeId(Integer orderTypeId);
    Business getBusinessById(Integer businessId);
    List<Business> getBusinessesByUserId(Integer userId);
    String createBusiness(BusinessVo businessVo);
    String deleteBusienss(Integer businessId);
    String updateBusiness(Business business);
}
