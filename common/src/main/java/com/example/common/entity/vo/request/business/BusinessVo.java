package com.example.common.entity.vo.request.business;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 需要填写或者修改的business信息
 */
@Data
public class BusinessVo {
    //商家名称
    private String businessName;
    //商家地址
    private String businessAddress;
    //商家介绍
    private String businessExplain;
    //商家图片
    private String businessImg;
    //点餐分类
    private Integer orderTypeId;
    //起送费
    private BigDecimal starPrice;
    //配送费")
    private BigDecimal deliveryPrice;
    //门店所属商家
    private Integer userId;
    //备注
    private String remarks;
}
