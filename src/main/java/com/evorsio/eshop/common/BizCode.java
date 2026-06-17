package com.evorsio.eshop.common;

import lombok.Getter;

/**
 * @author Evorsio
 * @since 2026/6/16
 */
@Getter
public enum BizCode {
    SUCCESS(200, "操作成功"),
    FAIL(400, "系统繁忙请稍后再试"),
    PARAM_ERROR(4001, "参数校验失败"),
    SMS_CODE_ERROR(4002, "短信验证码错误"),
    SMS_CODE_FREQUENTLY(4003, "短信验证码发送频繁，请稍后再试"),
    NOT_LOGIN(401, "未登录"),
    PRODUCT_NOT_FOUND(4005, "商品未找到");
    private final Integer num;
    private final String message;

    BizCode(Integer num, String message) {
        this.num = num;
        this.message = message;
    }
}
