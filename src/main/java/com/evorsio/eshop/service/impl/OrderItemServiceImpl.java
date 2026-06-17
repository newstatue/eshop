package com.evorsio.eshop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.evorsio.eshop.domain.OrderItem;
import com.evorsio.eshop.service.OrderItemService;
import com.evorsio.eshop.mapper.OrderItemMapper;
import org.springframework.stereotype.Service;

/**
* @author Admin
* @description 针对表【order_item】的数据库操作Service实现
* @createDate 2026-06-16 00:58:31
*/
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem>
    implements OrderItemService{

}




