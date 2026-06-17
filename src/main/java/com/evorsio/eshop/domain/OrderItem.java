package com.evorsio.eshop.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 *
 * @TableName order_item
 */
@TableName(value = "order_item")
@Data
public class OrderItem {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * SKU ID
     */
    private Long skuId;

    /**
     * 规格快照
     */
    private String skuSpec;

    /**
     * 商品名快照
     */
    private String spuName;

    /**
     * 下单时价格快照
     */
    private BigDecimal price;

    /**
     * 购买数量
     */
    private Integer quantity;
}