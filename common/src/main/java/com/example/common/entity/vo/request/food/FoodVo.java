package com.example.common.entity.vo.request.food;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 食品的新增
 */
@Data
public class FoodVo {
    //食品名称
    private String foodName;
    //食品介绍
    private String foodExplain;
    //食品图片
    private String foodImg;
    //食品价格
    private BigDecimal foodPrice;
    //所属商家编号
    private Integer businessId;
    //备注
    private String remarks;
}
