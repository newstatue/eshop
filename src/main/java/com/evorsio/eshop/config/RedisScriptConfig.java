package com.evorsio.eshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import java.util.List;

/**
 * @author Evorsio
 * @since 2026/6/17
 */
@Configuration
public class RedisScriptConfig {
    @Bean
    public DefaultRedisScript<List> deductStockScript(){
        DefaultRedisScript<List> script = new DefaultRedisScript<>();
        script.setScriptSource(new ResourceScriptSource(
                new ClassPathResource("lua/deduct_stock.lua")
        ));
        script.setResultType(List.class);
        return script;
    }
}
