package com.evorsio.eshop.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Evorsio
 * @since 2026/6/16
 */
@Data
public class SpuDetailVo {
    private Long id;
    private String name;
    private String description;
    private List<SkuVo> skuList;
    private List<String> images;
}
