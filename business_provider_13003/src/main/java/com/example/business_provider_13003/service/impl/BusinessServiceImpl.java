package com.example.business_provider_13003.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.business_provider_13003.mapper.BusinessMapper;
import com.example.business_provider_13003.service.BusinessService;
import com.example.common.entity.dto.Business;
import com.example.common.entity.vo.request.business.BusinessVo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author root
 * @since 2023-09-21
 */
@Service
public class BusinessServiceImpl extends ServiceImpl<BusinessMapper, Business> implements BusinessService {

    @Resource
    private BusinessMapper businessMapper;
    /**
     * 获取全部商家列表
     * @return
     */
    @Override
    public List<Business> getAllBusinesses() {
        QueryWrapper<Business> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("delTag",1);
        return businessMapper.selectList(queryWrapper);
    }
    /**
     * mybatisPlus-QueryWrapper多条件查询
     * 根据商家类型编号查询某一类型商家信息
     * @param orderTypeId
     * @return
     */
    @Override
    public List<Business> listBusinessesByOrderTypeId(Integer orderTypeId) {
        QueryWrapper<Business> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("orderTypeId", orderTypeId)
                .eq("delTag",1);
        return businessMapper.selectList(queryWrapper);
    }
    /**
     * 根据商家Id查询商家信息
     * @param businessId
     * @return
     */
    @Override
    public Business getBusinessById(Integer businessId) {
        return businessMapper.selectById(businessId);
    }

    @Override
    public List<Business> getBusinessesByUserId(Integer userId) {
        QueryWrapper<Business> businessQueryWrapper=new QueryWrapper<>();
        businessQueryWrapper
                .eq("userId",userId)
                .eq("delTag",1);
        return  businessMapper.selectList(businessQueryWrapper);
    }


    /**
     * 新建business
     * @param businessVo
     * @return
     */
    @Override
    public String createBusiness(BusinessVo businessVo) {
        Business business = new Business(
                null,
                businessVo.getBusinessName(),
                businessVo.getBusinessAddress(),
                businessVo.getBusinessExplain(),
                businessVo.getBusinessImg(),
                businessVo.getOrderTypeId(),
                businessVo.getStarPrice(),
                businessVo.getDeliveryPrice(),
                new Date(),
                businessVo.getUserId(),
                businessVo.getRemarks(),
                1
        );
        if (businessMapper.insert(business) == 1) {
            return null;
        } else {
            return "新增商家失败";
        }
    }

    /**
     * 软删除business
     * @param businessId
     * @return
     */
    @Override
    public String deleteBusienss(Integer businessId) {
        UpdateWrapper<Business> businessUpdateWrapper = new UpdateWrapper<>();
        businessUpdateWrapper.eq("businessId", businessId)
                .set("delTag", 0);
        if (businessMapper.update(null, businessUpdateWrapper) == 1) {
            return null;
        } else {
            return "删除商家失败";
        }
    }


    /**
     * 修改商家信息
     * @param business
     * @return
     */
    @Override
    public String updateBusiness(Business business) {
        UpdateWrapper<Business> businessUpdateWrapper=new UpdateWrapper<>();
        businessUpdateWrapper.eq("businessId",business.getBusinessId())
                .set("businessName",business.getBusinessName())
                .set("businessAddress",business.getBusinessAddress())
                .set("businessExplain",business.getBusinessExplain())
                .set("businessImg",business.getBusinessImg())
                .set("orderTypeId",business.getOrderTypeId())
                .set("starPrice",business.getStarPrice())
                .set("deliveryPrice",business.getDeliveryPrice())
                .set("remarks",business.getRemarks());
        if(businessMapper.update(null,businessUpdateWrapper)==1){
            return null;
        }
        else {
            return "修改商家信息成功";
        }
    }

}
