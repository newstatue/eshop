package com.evorsio.eshop.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.math.BigDecimal;
import lombok.Data;

/**
 * 
 * @TableName order
 */
@TableName(value ="order")
@Data
public class Order {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;

    /**
     * 0待付款 1已付款 2已取消
     */
    private Integer status;

    /**
     * 
     */
    @TableLogic
    private Integer deleted;
}