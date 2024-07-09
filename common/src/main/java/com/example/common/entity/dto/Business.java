package com.example.common.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.common.entity.BaseData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author root
 * @since 2023-09-21
 */
@Data
@TableName("business")
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Business implements BaseData {
    //商家编号
    @TableId(value = "businessId", type = IdType.AUTO)
    private Integer businessId;
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
    //商家注册时间
    @TableField("registerTime")
    private Date registerTime;
    //门店所属商家
    @TableField("userId")
    private Integer userId;
    //备注
    private String remarks;
    //删除标记（1：正常； 0：删除）
    @TableField("delTag")
    private Integer delTag;

}
