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

/**
 * <p>
 * 
 * </p>
 *
 * @author root
 * @since 2023-09-21
 */
@Data
@TableName("deliveryaddress")
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DeliveryAddress implements BaseData {
    //送货地址编号
    @TableId(value = "daId", type = IdType.AUTO)
    private Integer daId;
    //联系人姓名
    @TableField("contactName")
    private String contactName;
    //联系人性别
    @TableField("contactSex")
    private Integer contactSex;
    //联系人电话
    @TableField("contactTel")
    private String contactTel;
    // 送货地址
    @TableField("address")
    private String address;
    //所属用户编号
    @TableField("userId")
    private Integer userId;
    //删除标记（1：正常； 0：删除）
    @TableField("delTag")
    private Integer delTag;

}
