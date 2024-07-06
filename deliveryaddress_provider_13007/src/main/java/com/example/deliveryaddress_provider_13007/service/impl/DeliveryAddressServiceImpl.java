package com.example.deliveryaddress_provider_13007.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.entity.dto.Business;
import com.example.common.entity.dto.DeliveryAddress;
import com.example.common.entity.vo.request.deliveryaddress.DeliveryAddressVo;
import com.example.deliveryaddress_provider_13007.mapper.DeliveryAddressMapper;
import com.example.deliveryaddress_provider_13007.service.DeliveryAddressService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

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
public class DeliveryAddressServiceImpl extends ServiceImpl<DeliveryAddressMapper, DeliveryAddress> implements DeliveryAddressService {

    @Resource
    private DeliveryAddressMapper deliveryAddressMapper;

    /**
     * 根据用户id列出该用户所有收货地址信息
     * @param userId
     * @return
     */
    @Override
    public List<DeliveryAddress> listDeliveryAddressByUserId(Integer userId) {
        QueryWrapper<DeliveryAddress> queryWrapper=new QueryWrapper<>();
        queryWrapper
                .eq("userId",userId)
                .eq("delTag",1);
        return deliveryAddressMapper.selectList(queryWrapper);
    }

    /**
     * 根据地址id查询地址详细信息
     * @param daId
     * @return
     */
    @Override
    public DeliveryAddress getDeliveryAddressById(Integer daId) {
        return deliveryAddressMapper.selectById(daId);
    }

    /**
     * 新增收货地址
     * @param vo
     * @return
     */
    @Override
    public String createDeliveryAddress(DeliveryAddressVo vo) {
        DeliveryAddress da=new DeliveryAddress(
                null,
                vo.getContactName(),
                vo.getContactSex(),
                vo.getContactTel(),
                vo.getAddress(),
                vo.getUserId(),
                1);
        if (deliveryAddressMapper.insert(da)==1){
            return null;
        }else {
            return "保存地址失败";
        }
    }

    /**
     * 更新收获地址
     * @param deliveryAddress
     * @return
     */
    @Override
    public String updateDeliveryAddress(DeliveryAddress deliveryAddress) {
        UpdateWrapper<DeliveryAddress> deliveryAddressUpdateWrapper=new UpdateWrapper<>();
        deliveryAddressUpdateWrapper.eq("daId",deliveryAddress.getDaId())
                .set("address", deliveryAddress.getAddress())
                .set("contactTel",deliveryAddress.getContactTel())
                .set("contactName",deliveryAddress.getContactName())
                .set("contactSex",deliveryAddress.getContactSex());
        int update=deliveryAddressMapper.update(null, deliveryAddressUpdateWrapper);
        if (update==1){
            return null;
        }else {
            return "修改地址失败";
        }
    }

    /**
     * 软删除收货地址
     * @param daId
     * @return
     */
    @Override
    public String deleteDeliveryAddress(Integer daId) {
        UpdateWrapper<DeliveryAddress> deliveryAddressUpdateWrapper = new UpdateWrapper<>();
        deliveryAddressUpdateWrapper.eq("daId", daId)
                .set("delTag", 0);
        if (deliveryAddressMapper.update(null, deliveryAddressUpdateWrapper) == 1) {
            return null;
        } else {
            return "删除地址失败";
        }
    }
}
