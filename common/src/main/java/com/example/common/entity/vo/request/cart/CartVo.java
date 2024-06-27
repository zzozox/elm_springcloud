package com.example.common.entity.vo.request.cart;

import lombok.Data;

/**
 * 前端传以下值到后端，进行saveCart
 */
@Data
public class CartVo {
    Integer foodId;
    Integer businessId;
    Integer userId;
    Integer quantity;
}
