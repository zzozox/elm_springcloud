package com.example.common.entity.vo.request.order;

import lombok.Data;

import java.math.BigDecimal;


/**
 * 前端传以下值到后端，进行saveOrder
 */
@Data
public class OrderVo {
    private Integer userId;
    private Integer businessId;
    private BigDecimal orderTotal;
    private Integer daId;

}
