package com.evorsio.eshop.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 *
 * @TableName stock_log
 */
@TableName(value = "stock_log")
@Data
public class StockLog {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * SKU ID
     */
    private Long skuId;

    /**
     * 关联订单号
     */
    private String orderNo;

    /**
     * 扣减前库存(Redis快照)
     */
    private Integer beforeQty;

    /**
     * 扣减数量
     */
    private Integer deductQty;

    /**
     * 扣减后库存(Redis快照)
     */
    private Integer afterQty;

    /**
     *
     */
    private Date createdAt;
}