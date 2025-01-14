package com.example.order_provider_13011.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.entity.dto.Cart;
import com.example.common.entity.dto.OrderDetailet;
import com.example.common.entity.dto.Orders;
import com.example.common.entity.vo.request.order.OrderVo;
import com.example.order_provider_13011.mapper.CartMapper;
import com.example.order_provider_13011.mapper.OrderDetailetMapper;
import com.example.order_provider_13011.mapper.OrdersMapper;
import com.example.order_provider_13011.service.OrdersService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {
    @Resource
    private OrdersMapper ordersMapper;

    @Resource
    private CartMapper cartMapper;

    @Resource
    private OrderDetailetMapper orderDetailetMapper;

    /**
     * 生成订单
     * @param vo
     * @return
     */
    @Override
    public Orders createOrders(OrderVo vo) {
        //0为未支付
        //1为未删除
        Orders orders=new Orders(
                null,
                vo.getUserId(),
                vo.getBusinessId(),
                LocalDateTime.now().toString(),
                vo.getOrderTotal(),
                vo.getDaId(),
                0,
                1);
        if(ordersMapper.insert(orders)==1) {
            //在生成订单的同时再生成订单明细
            //在支付页面的订单明细直接由订单确认界面传给支付界面
            QueryWrapper<Cart> cartQueryWrapper = new QueryWrapper<>();
            cartQueryWrapper.eq("userId", orders.getUserId())
                    .eq("businessId", orders.getBusinessId());
            List<Cart> carts=cartMapper.selectList(cartQueryWrapper);
            for(Cart cart : carts){
                OrderDetailet orderDetailet=new OrderDetailet(
                    null,
                    orders.getOrderId(),
                    cart.getFoodId(),
                    cart.getQuantity()
                );
                orderDetailetMapper.insert(orderDetailet);
            }
            //订单生成成功后删除购物车记录，即该用户在该商家下的购物车记录
            cartMapper.delete(cartQueryWrapper);
            return orders;
        }else {
            return null;
        }
    }

    /**
     * 通过订单编号查询订单详情
     * @param orderId
     * @return
     */
    @Override
    public Orders getOrdersById(Integer orderId) {
        return ordersMapper.selectById(orderId);
    }

    /**
     * 用户id查询用户所有订单
     * @param userId
     * @return
     */
    @Override
    public List<Orders> listOrdersByUserId(Integer userId) {
        QueryWrapper<Orders> ordersQueryWrapper=new QueryWrapper<>();
        ordersQueryWrapper
                .eq("userId",userId)
                .eq("delTag",1);
        return ordersMapper.selectList(ordersQueryWrapper);
    }

    @Override
    public String payOrder(Integer orderId) {
        //1为已支付
        UpdateWrapper<Orders> ordersUpdateWrapper=new UpdateWrapper<>();
        ordersUpdateWrapper
                .eq("orderId",orderId)
                .set("orderState",1);
        if (ordersMapper.update(ordersUpdateWrapper)==1){
            return null;
        }
        else return "支付失败";
    }
}
