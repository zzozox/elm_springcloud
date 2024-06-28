package com.example.food_consumer_13006.controller;

import com.example.food_consumer_13006.controller.apiClient.FoodApiClient;
import com.example.common.entity.RestBean;
import com.example.common.entity.dto.Food;
import com.example.common.entity.vo.request.food.FoodVo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodController {

    @Resource
    private FoodApiClient foodApiClient;

    @PostMapping("/byBusinessId/{businessId}")
    public RestBean<List<Food>> foodByBusinessId(@PathVariable Integer businessId) {
        return foodApiClient.foodByBusinessId(businessId);
    }

    @PostMapping("/byFoodId/{foodId}")
    public RestBean<Food> findFoodByFoodId(@PathVariable Integer foodId) {
        return foodApiClient.findFoodByFoodId(foodId);
    }

    @PostMapping("/createFood")
    public RestBean<Void> createFood(@RequestBody FoodVo foodVo) {
        return foodApiClient.createFood(foodVo);
    }

    @PostMapping("/deleteFood/{foodId}")
    public RestBean<Void> deleteFood(@PathVariable Integer foodId) {
        return foodApiClient.deleteFood(foodId);
    }

    @PostMapping("/updateFood")
    public RestBean<Void> updateFood(@RequestBody Food food) {
        return foodApiClient.updateFood(food);
    }
}
