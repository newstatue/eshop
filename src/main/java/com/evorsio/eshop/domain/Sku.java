package com.evorsio.eshop.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 *
 * @TableName sku
 */
@TableName(value = "sku")
@Data
public class Sku {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 关联SPU
     */
    private Long spuId;

    /**
     * 规格 如: 黑色/128G
     */
    private String spec;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 库存
     */
    private Integer stock;

    /**
     *
     */
    @TableLogic
    private Integer deleted;
}