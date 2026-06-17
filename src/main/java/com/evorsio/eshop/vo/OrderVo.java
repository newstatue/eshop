package com.evorsio.eshop.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Evorsio
 * @since 2026/6/17
 */
@Data
public class OrderVo {
    private Long orderId;
    private String orderNo;
    private BigDecimal totalAmount;
}
