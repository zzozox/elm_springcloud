package com.example.food_provider_13005.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.entity.dto.Food;
import com.example.common.entity.vo.request.food.FoodVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author root
 * @since 2023-09-21
 */
public interface FoodService extends IService<Food> {
    List<Food> listFoodByBusinessId(Integer businessId);
    Food getFoodById(Integer foodId);
    String createFood(FoodVo foodVo);
    String deleteFood(Integer foodId);
    String updateFood(Food food);
}
