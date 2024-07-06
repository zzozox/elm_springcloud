package com.example.food_provider_13005.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.entity.dto.Business;
import com.example.common.entity.dto.Food;
import com.example.common.entity.vo.request.food.FoodVo;
import com.example.food_provider_13005.mapper.FoodMapper;
import com.example.food_provider_13005.service.FoodService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;
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
public class FoodServiceImpl extends ServiceImpl<FoodMapper, Food> implements FoodService {
    @Resource
    private FoodMapper foodMapper;

    /**
     * 根据商家id查询商家所属食物
     * @param businessId
     * @return
     */
    @Override
    public List<Food> listFoodByBusinessId(Integer businessId) {
        QueryWrapper<Food> foodQueryWrapper=new QueryWrapper<>();
        foodQueryWrapper
                .eq("businessId",businessId)
                .eq("delTag",1);
        return foodMapper.selectList(foodQueryWrapper);
    }

    /**
     * 根据食品编号查询食品详细信息
     * @param foodId
     * @return
     */
    @Override
    public Food getFoodById(Integer foodId) {
        return foodMapper.selectById(foodId);
    }

    /**
     * 在商家下新建食品
     * @param foodVo
     * @return
     */
    @Override
    public String createFood(FoodVo foodVo) {
        Food food = new Food(
                null,
                foodVo.getFoodName(),
                foodVo.getFoodExplain(),
                foodVo.getFoodImg(),
                foodVo.getFoodPrice(),
                foodVo.getBusinessId(),
                new Date(),
                foodVo.getRemarks(),
                1
        );
        if (foodMapper.insert(food) == 1) {
            return null;
        }
        else {
            return "创建食品失败";
        }
    }

    /**
     * 软删除食品
     * @param foodId
     * @return
     */
    @Override
    public String deleteFood(Integer foodId) {
        UpdateWrapper<Food> foodUpdateWrapper = new UpdateWrapper<>();
        foodUpdateWrapper.eq("foodId", foodId)
                .set("delTag", 0);
        if (foodMapper.update(null, foodUpdateWrapper) == 1) {
            return null;
        } else {
            return "删除食品失败";
        }
    }

    /**
     * 修改食品信息
     * @param food
     * @return
     */
    @Override
    public String updateFood(Food food) {
        UpdateWrapper<Food> foodUpdateWrapper = new UpdateWrapper<>();
        foodUpdateWrapper.eq("foodId",food.getFoodId())
                .set("foodName",food.getFoodName())
                .set("foodExplain",food.getFoodExplain())
                .set("foodImg",food.getFoodImg())
                .set("foodPrice",food.getFoodPrice())
                .set("remarks",food.getRemarks());
        if(foodMapper.update(null,foodUpdateWrapper)==1){
            return null;
        }
        else {
            return "修改食品信息失败";
        }
    }
}
