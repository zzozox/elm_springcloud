package com.example.cart_provider_13009.controller;

import com.example.cart_provider_13009.service.CartService;
import com.example.common.entity.RestBean;
import com.example.common.entity.dto.Cart;
import com.example.common.entity.vo.request.cart.CartListVo;
import com.example.common.entity.vo.request.cart.CartVo;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author root
 * @since 2023-09-21
 */
/**
 * cart相关Controller
 */
@RestController
@RequestMapping("/cart")
public class CartController {
    @Resource
    private CartService cartService;

    /**
     * 列出当前用户所拥有的全部购物车内容
     * @param userId
     * @return
     */
    @PostMapping("/listCartByUserId/{userId}")
    public RestBean<List<Cart>> listCartByUserId(@PathVariable Integer userId){
        return RestBean.success(cartService.listCartByAccountId(userId));
    }

    /**
     * 列出当前用户在当前商家下的所有购物车
     * @param vo
     * @return
     */
    @PostMapping("/listCartByUserIdAndBusinessId")
    public RestBean<List<Cart>> listCartByUserIdAndBusinessId(@RequestBody @Valid CartListVo vo){
        return RestBean.success(cartService.listCartByAccountIdAndBusinessId(vo));
    }

    /**
     * 当该食品数量为0时，添加购物车做insert
     * @param vo
     * @return
     */
    @PostMapping("/createCart")
    public RestBean<Void> createCart(@RequestBody @Valid CartVo vo){
        return this.messageHandle(() ->
                cartService.createCart(vo));
    }

    /**
     * 当该食品数量不为0时，添加购物车做update
     * @param cart
     * @return
     */
    @PostMapping ("/updateCart")
    public RestBean<Void> updateCart(@RequestBody @Valid Cart cart){
        return this.messageHandle(() ->
                cartService.updateCart(cart));
    }

    /**
     * 移出购物车
     * @param cartId
     * @return
     */
    @RequestMapping("/deleteCart/{cartId}")
    public RestBean<Void> deleteCart(@PathVariable Integer cartId){
        return this.messageHandle(() ->
                cartService.deleteCart(cartId));
    }

    private <T> RestBean<T> messageHandle(Supplier<String> action){
        String message = action.get();
        if(message == null)
            return RestBean.success();
        else
            return RestBean.failure(400, message);
    }
}

