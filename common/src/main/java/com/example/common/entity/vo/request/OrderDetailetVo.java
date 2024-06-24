package com.example.common.entity.vo.request;

import com.example.common.entity.dto.Business;
import com.example.common.entity.dto.Orders;
import lombok.Data;

import java.util.List;

/**
 * saveOrderDetailet
 */
@Data
public class OrderDetailetVo{
    private Orders orders;
    private Business business;
    private List<OrderDetailetListVo> orderDetailetListVos;
}
