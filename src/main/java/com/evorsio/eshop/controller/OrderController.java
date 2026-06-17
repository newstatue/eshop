package com.evorsio.eshop.controller;

import com.evorsio.eshop.common.R;
import com.evorsio.eshop.service.OrderService;
import com.evorsio.eshop.vo.CreatOrderVo;
import com.evorsio.eshop.vo.OrderVo;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Evorsio
 * @since 2026/6/16
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create")
    public R<OrderVo> createOrder(@Validated @RequestBody CreatOrderVo vo){
        OrderVo o = orderService.createOrder(vo);
        return R.ok(o);
    }
}
