package com.evorsio.eshop.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;

import java.util.Date;

/**
 * @author Evorsio
 * @since 2026/6/17
 */
public class OrderUtil {
    public static String generateOrderNo() {
        return DateUtil.format(new Date(), "yyyyMMddHHmmss")
                + RandomUtil.randomNumbers(6);
    }
}
