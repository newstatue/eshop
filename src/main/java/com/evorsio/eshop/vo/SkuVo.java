package com.evorsio.eshop.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Evorsio
 * @since 2026/6/16
 */
@Data
public class SkuVo {
    private Long id;
    private String spec;
    private BigDecimal price;
    private Integer stock;
}
