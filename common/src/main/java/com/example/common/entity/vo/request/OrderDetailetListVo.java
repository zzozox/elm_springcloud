package com.example.common.entity.vo.request;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 前端查询当前用户当前商家下购物车中食品的详细信息
 * param CartListVo
 */
@Data
public class OrderDetailetListVo {
    private Integer foodId;
    private String foodName;
    private String foodImg;
    private BigDecimal foodPrice;
    private Integer quantity;
}
