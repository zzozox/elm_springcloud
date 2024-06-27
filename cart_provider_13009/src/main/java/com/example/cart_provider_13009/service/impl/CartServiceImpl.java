package com.example.cart_provider_13009.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.cart_provider_13009.mapper.CartMapper;
import com.example.cart_provider_13009.service.CartService;
import com.example.common.entity.dto.Cart;
import com.example.common.entity.vo.request.cart.CartListVo;
import com.example.common.entity.vo.request.cart.CartVo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author root
 * @since 2023-09-21
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {
    @Resource
    CartMapper cartMapper;
    /**
     * 根据用户编号查询此用户所有购物车信息
     * @param userId
     * @return
     */
    @Override
    public List<Cart> listCartByAccountId(Integer userId) {
        QueryWrapper<Cart> cartQueryWrapper=new QueryWrapper<>();
        cartQueryWrapper.eq("userId",userId);
        return cartMapper.selectList(cartQueryWrapper);
    }

    /**
     * 根据用户编号和商家编号，查询此用户购物车中某个商家的所有购物车信息
     * @param vo
     * @return
     */
    @Override
    public List<Cart> listCartByAccountIdAndBusinessId(CartListVo vo) {
        QueryWrapper<Cart> cartQueryWrapper=new QueryWrapper<>();
        cartQueryWrapper.eq("userId",vo.getUserId())
                    .eq("businessId",vo.getBusinessId());
        return cartMapper.selectList(cartQueryWrapper);
    }

    /**
     * 购物车内食品数量为0时所insert
     * @param vo
     * @return
     */
    @Override
    public String createCart(CartVo vo) {
        Cart cart=new Cart(null, vo.getFoodId(), vo.getBusinessId(), vo.getUserId(), vo.getQuantity());
        if(cartMapper.insert(cart)==1){
            return null;
        }else {
            return "添加购物车失败";
        }
    }

    /**
     * 购物车内食品数量不为0时所updata
     * @param cart
     * @return
     */
    @Override
    public String updateCart(Cart cart) {
       UpdateWrapper<Cart> cartUpdateWrapper=new UpdateWrapper<>();
       cartUpdateWrapper.eq("foodId", cart.getFoodId())
                .eq("businessId",cart.getBusinessId())
                .eq("userId",cart.getUserId())
                .set("quantity", cart.getQuantity());
       int update=cartMapper.update(null,cartUpdateWrapper);
        if (update==1){
            return null;
        }else {
            return "添加购物车失败";
        }
    }

    /**
     * 移除购物车
     * @param cartId
     * @return
     */
    @Override
    public String deleteCart(Integer cartId) {
        if(cartMapper.deleteById(cartId)==1){
            return null;
        }else{
            return "删除购物车失败";
        }
    }
}
