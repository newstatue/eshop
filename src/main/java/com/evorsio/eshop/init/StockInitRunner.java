package com.evorsio.eshop.init;

import com.evorsio.eshop.common.BizConstants;
import com.evorsio.eshop.domain.Sku;
import com.evorsio.eshop.mapper.SkuMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Evorsio
 * @since 2026/6/17
 * 启动时将sku库存写入Redis
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StockInitRunner implements ApplicationRunner {
    private final SkuMapper skuMapper;
    private final StringRedisTemplate redisTemplate;

    @Override
    public void run(ApplicationArguments args) {
        List<Sku> skus = skuMapper.selectList(null);
        for (Sku sku : skus) {
            redisTemplate.opsForValue().set(
                    BizConstants.REDIS_KEY_SKU_STOCK_PREFIX + sku.getId(),
                    String.valueOf(sku.getStock())
            );
        }
        log.info("库存写入Redis成功");
    }
}
