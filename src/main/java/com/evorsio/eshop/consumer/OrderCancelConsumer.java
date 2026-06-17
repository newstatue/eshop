package com.evorsio.eshop.consumer;

import com.evorsio.eshop.common.BizConstants;
import com.evorsio.eshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author Evorsio
 * @since 2026/6/17
 */
@Slf4j
@Service
@RequiredArgsConstructor
@RocketMQMessageListener(
        topic = BizConstants.MQ_TOPIC_ORDER_CANCEL,
        consumerGroup = "eshop-order-cancel-group"
)
public class OrderCancelConsumer implements RocketMQListener<String> {
    private final OrderService orderService;

    @Override
    public void onMessage(String orderNo) {
        log.info("收到超时检查消息 orderNo={}", orderNo);
        orderService.cancelIfUnpaid(orderNo);
    }
}
