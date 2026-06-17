package com.evorsio.eshop.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Evorsio
 * @since 2026/6/16
 */
@Data
public class SpuVo {
    private Long id;
    private String name;
    private String description;
    private BigDecimal minPrice;
    private String mainImage;
}
