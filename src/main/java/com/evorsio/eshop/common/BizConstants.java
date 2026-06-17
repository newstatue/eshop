package com.evorsio.eshop.common;

/**
 * @author Evorsio
 * @since 2026/6/16
 */
public class BizConstants {
    // 防重复下单锁 key 前缀
    public static final String REDIS_KEY_LOCK_ORDER_PREFIX = "lock:order:create:";
    public static final String MQ_TOPIC_ORDER_CANCEL = "order-cancel-topic";
    public static String USER_NAME_PREFIX = "用户";
    public static String REDIS_KEY_SMS_CODE_PREFIX = "sms:code:";
    public static String REDIS_KEY_SKU_STOCK_PREFIX = "sku:stock:";
}
