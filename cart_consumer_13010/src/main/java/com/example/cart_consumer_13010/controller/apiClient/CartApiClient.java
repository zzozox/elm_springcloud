package com.example.cart_consumer_13010.controller.apiClient;

import com.example.common.entity.RestBean;
import com.example.common.entity.dto.Cart;
import com.example.common.entity.vo.request.cart.CartListVo;
import com.example.common.entity.vo.request.cart.CartVo;
import jakarta.validation.Valid;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "cart-provider-13009", fallback = CartApiClientFallback.class)
@LoadBalancerClient(name = "cart-provider-13009")
public interface CartApiClient {

    @PostMapping("/cart/listCartByUserId/{userId}")
    RestBean<List<Cart>> listCartByUserId(@PathVariable Integer userId);

    @PostMapping("/cart/listCartByUserIdAndBusinessId")
    RestBean<List<Cart>> listCartByUserIdAndBusinessId(@RequestBody @Valid CartListVo vo);

    @PostMapping("/cart/createCart")
    RestBean<Void> createCart(@RequestBody @Valid CartVo vo);

    @PostMapping("/cart/updateCart")
    RestBean<Void> updateCart(@RequestBody @Valid Cart cart);

    @RequestMapping("/cart/deleteCart/{cartId}")
    RestBean<Void> deleteCart(@PathVariable Integer cartId);
}
