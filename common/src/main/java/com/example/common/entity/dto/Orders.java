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

import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author root
 * @since 2023-09-21
 */
@Data
@TableName("orders")
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Orders implements BaseData {
    //订单编号
    @TableId(value = "orderId", type = IdType.AUTO)
    private Integer orderId;
    //用户编号
    @TableField("userId")
    private Integer userId;
    //商家编号
    @TableField("businessId")
    private Integer businessId;
    //订购日期
    @TableField("orderDate")
    private String orderDate;
    //订单总价
    @TableField("orderTotal")
    private BigDecimal orderTotal;
    //送货地址编号
    @TableField("daId")
    private Integer daId;
    //订单状态（0：未支付； 1：已支付）
    @TableField("orderState")
    private Integer orderState;
    //删除标记（1：正常； 0：删除）
    @TableField("delTag")
    private Integer delTag;
}
