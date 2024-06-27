package com.example.food_provider_13005.controller;

import com.example.common.entity.RestBean;
import com.example.common.entity.dto.Food;
import com.example.common.entity.vo.request.food.FoodVo;
import com.example.food_provider_13005.service.FoodService;
import jakarta.annotation.Resource;
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
 * food相关Controller
 */
@RestController
@RequestMapping("/food")
public class FoodController {
    @Resource
    private FoodService foodService;

    /**
     * 根据商家编号查询该商家的食品
     * @param businessId
     * @return
     */
    @PostMapping ("/byBusinessId/{businessId}")
    public RestBean<List<Food>> FoodByBusinessId(@PathVariable Integer businessId) {
        List<Food> foods = foodService.listFoodByBusinessId(businessId);
        return RestBean.success(foods);
    }

    /**
     * 根据食品id查询食品信息
     * @param foodId
     * @return
     */
    @PostMapping("/byFoodId/{foodId}")
    public RestBean<Food> findFoodByFoodId(@PathVariable Integer foodId){
        return RestBean.success(foodService.getFoodById(foodId));
    }

    /**
     * 在商家下新建食品
     * @param foodVo
     * @return
     */
    @PostMapping("/createFood")
    public RestBean<Void> createFood(@RequestBody FoodVo foodVo) {
        return this.messageHandle(() ->foodService.createFood(foodVo));
    }

    /**
     * 软删除食品
     * @param foodId
     * @return
     */
    @PostMapping("/deleteFood/{foodId}")
    public RestBean<Void> deleteFood(@PathVariable Integer foodId) {
        return this.messageHandle(() ->foodService.deleteFood(foodId));
    }

    /**
     * 修改食品信息
     * @param food
     * @return
     */
    @PostMapping("/updateFood")
    public RestBean<Void> updateFood(@RequestBody Food food) {
        return this.messageHandle(() -> foodService.updateFood(food));
    }

    /**
     * 针对于返回值为String作为错误信息的方法进行统一处理
     * @param action 具体操作
     * @return 响应结果
     * @param <T> 响应结果类型
     */
    private <T> RestBean<T> messageHandle(Supplier<String> action){
        String message = action.get();
        if(message == null)
            return RestBean.success();
        else
            return RestBean.failure(400, message);
    }
}

