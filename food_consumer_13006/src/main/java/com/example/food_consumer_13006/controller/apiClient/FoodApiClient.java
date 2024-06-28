package com.example.food_consumer_13006.controller.apiClient;

import com.example.common.entity.RestBean;
import com.example.common.entity.dto.Food;
import com.example.common.entity.vo.request.food.FoodVo;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "food-provider-13005", fallback = com.example.food_consumer_13006.controller.apiClient.FoodApiClientFallback.class)
@LoadBalancerClient(name = "food-provider-13005")
public interface FoodApiClient {

    @PostMapping("/food/byBusinessId/{businessId}")
    RestBean<List<Food>> foodByBusinessId(@PathVariable Integer businessId);

    @PostMapping("/food/byFoodId/{foodId}")
    RestBean<Food> findFoodByFoodId(@PathVariable Integer foodId);

    @PostMapping("/food/createFood")
    RestBean<Void> createFood(@RequestBody FoodVo foodVo);

    @PostMapping("/food/deleteFood/{foodId}")
    RestBean<Void> deleteFood(@PathVariable Integer foodId);

    @PostMapping("/food/updateFood")
    RestBean<Void> updateFood(@RequestBody Food food);
}
