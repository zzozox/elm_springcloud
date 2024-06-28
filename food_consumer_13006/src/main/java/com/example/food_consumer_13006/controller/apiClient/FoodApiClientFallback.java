package com.example.food_consumer_13006.controller.apiClient;

import com.example.common.entity.RestBean;
import com.example.common.entity.dto.Food;
import com.example.common.entity.vo.request.food.FoodVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FoodApiClientFallback implements FoodApiClient {

    private static final Logger logger = LoggerFactory.getLogger(FoodApiClientFallback.class);

    @Override
    public RestBean<List<Food>> foodByBusinessId(Integer businessId) {
        logFallback("foodByBusinessId", "businessId: " + businessId);
        return RestBean.failure(500, "服务不可用，无法根据商家ID查询食品");
    }

    @Override
    public RestBean<Food> findFoodByFoodId(Integer foodId) {
        logFallback("findFoodByFoodId", "foodId: " + foodId);
        return RestBean.failure(500, "服务不可用，无法根据食品ID查询食品信息");
    }

    @Override
    public RestBean<Void> createFood(FoodVo foodVo) {
        logFallback("createFood", "foodVo: " + foodVo);
        return RestBean.failure(500, "服务不可用，无法创建食品");
    }

    @Override
    public RestBean<Void> deleteFood(Integer foodId) {
        logFallback("deleteFood", "foodId: " + foodId);
        return RestBean.failure(500, "服务不可用，无法删除食品");
    }

    @Override
    public RestBean<Void> updateFood(Food food) {
        logFallback("updateFood", "food: " + food);
        return RestBean.failure(500, "服务不可用，无法更新食品信息");
    }

    private void logFallback(String methodName, String params) {
        logger.error("Fallback triggered for method: {}. Params: {}. Possible reasons: service is down or network issue.", methodName, params);
    }
}
