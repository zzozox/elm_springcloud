package com.example.cart_consumer_13010.controller.apiClient;

import com.example.common.entity.RestBean;
import com.example.common.entity.dto.Cart;
import com.example.common.entity.vo.request.cart.CartListVo;
import com.example.common.entity.vo.request.cart.CartVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartApiClientFallback implements com.example.cart_consumer_13010.controller.apiClient.CartApiClient {

    private static final Logger logger = LoggerFactory.getLogger(CartApiClientFallback.class);

    @Override
    public RestBean<List<Cart>> listCartByUserId(Integer userId) {
        logFallback("listCartByUserId", "userId: " + userId);
        return RestBean.failure(500, "服务不可用，无法列出用户购物车内容");
    }

    @Override
    public RestBean<List<Cart>> listCartByUserIdAndBusinessId(CartListVo vo) {
        logFallback("listCartByUserIdAndBusinessId", "vo: " + vo);
        return RestBean.failure(500, "服务不可用，无法列出用户在商家下的购物车");
    }

    @Override
    public RestBean<Void> createCart(CartVo vo) {
        logFallback("createCart", "cartVo: " + vo);
        return RestBean.failure(500, "服务不可用，无法创建购物车");
    }

    @Override
    public RestBean<Void> updateCart(Cart cart) {
        logFallback("updateCart", "cart: " + cart);
        return RestBean.failure(500, "服务不可用，无法更新购物车");
    }

    @Override
    public RestBean<Void> deleteCart(Integer cartId) {
        logFallback("deleteCart", "cartId: " + cartId);
        return RestBean.failure(500, "服务不可用，无法删除购物车");
    }

    private void logFallback(String methodName, String params) {
        logger.error("Fallback triggered for method: {}. Params: {}. Possible reasons: service is down or network issue.", methodName, params);
    }
}
