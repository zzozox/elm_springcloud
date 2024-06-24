package com.example.common.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.common.entity.BaseData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author root
 * @since 2023-09-21
 */
@Data
@TableName("food")
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Food implements BaseData {
    //食品编号
    @TableId(value = "foodId", type = IdType.AUTO)
    private Integer foodId;
    //食品名称
    @TableField("foodName")
    private String foodName;
    //食品介绍
    @TableField("foodExplain")
    private String foodExplain;
    //食品图片
    @TableField("foodImg")
    private String foodImg;
    //食品价格
    @TableField("foodPrice")
    private BigDecimal foodPrice;
    //所属商家编号
    @TableField("businessId")
    private Integer businessId;
    //食品数量
    @TableField("quantity")
    private Integer quantity;
    //食品创建时间
    @TableField("createTime")
    private Date createTime;
    //备注
    private String remarks;
    //删除标记（1：正常； 0：删除）
    @TableField("delTag")
    private Integer delTag;

}
