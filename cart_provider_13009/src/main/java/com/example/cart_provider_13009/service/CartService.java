package com.example.cart_provider_13009.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.entity.dto.Cart;
import com.example.common.entity.vo.request.cart.CartListVo;
import com.example.common.entity.vo.request.cart.CartVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author root
 * @since 2023-09-21
 */
public interface CartService extends IService<Cart> {
    List<Cart> listCartByAccountId(Integer userId);
    List<Cart> listCartByAccountIdAndBusinessId(CartListVo vo);
    String createCart(CartVo vo);
    String updateCart(Cart cart);
    String deleteCart(Integer cartId);
}
