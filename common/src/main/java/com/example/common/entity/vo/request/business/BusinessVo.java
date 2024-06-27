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
    @TableField("businessName")
    private String businessName;
    //商家地址
    @TableField("businessAddress")
    private String businessAddress;
    //商家介绍
    @TableField("businessExplain")
    private String businessExplain;
    //商家图片
    @TableField("businessImg")
    private String businessImg;
    //点餐分类
    @TableField("orderTypeId")
    private Integer orderTypeId;
    //起送费
    @TableField("starPrice")
    private BigDecimal starPrice;
    //配送费
    @TableField("deliveryPrice")
    private BigDecimal deliveryPrice;
    //备注
    private String remarks;
}
