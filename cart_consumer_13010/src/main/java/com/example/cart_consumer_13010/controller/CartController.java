package com.example.cart_consumer_13010.controller;

import com.example.cart_consumer_13010.controller.apiClient.CartApiClient;
import com.example.common.entity.RestBean;
import com.example.common.entity.dto.Cart;
import com.example.common.entity.vo.request.cart.CartListVo;
import com.example.common.entity.vo.request.cart.CartVo;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Resource
    private CartApiClient cartApiClient;

    @PostMapping("/listCartByUserId/{userId}")
    public RestBean<List<Cart>> listCartByUserId(@PathVariable Integer userId) {
        return cartApiClient.listCartByUserId(userId);
    }

    @PostMapping("/listCartByUserIdAndBusinessId")
    public RestBean<List<Cart>> listCartByUserIdAndBusinessId(@RequestBody @Valid CartListVo vo) {
        return cartApiClient.listCartByUserIdAndBusinessId(vo);
    }

    @PostMapping("/createCart")
    public RestBean<Void> createCart(@RequestBody @Valid CartVo vo) {
        return cartApiClient.createCart(vo);
    }

    @PostMapping("/updateCart")
    public RestBean<Void> updateCart(@RequestBody @Valid Cart cart) {
        return cartApiClient.updateCart(cart);
    }

    @RequestMapping("/deleteCart/{cartId}")
    public RestBean<Void> deleteCart(@PathVariable Integer cartId) {
        return cartApiClient.deleteCart(cartId);
    }
}
