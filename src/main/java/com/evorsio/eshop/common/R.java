package com.evorsio.eshop.common;

import lombok.Data;

/**
 * @author Evorsio
 * @since 2026/6/16
 * 业务统一返回
 */
@Data
public class R<T> {

    private Integer code;
    private String message;
    private T data;

    // ===== 成功 =====
    public static <T> R<T> ok() {
        return result(BizCode.SUCCESS, null);
    }

    public static <T> R<T> ok(T data) {
        return result(BizCode.SUCCESS, data);
    }

    // ===== 失败 =====
    public static <T> R<T> fail() {
        return result(BizCode.FAIL, null);
    }

    public static <T> R<T> fail(String message) {
        return result(BizCode.FAIL.getNum(), message, null);
    }

    public static <T> R<T> fail(Integer code, String message) {
        return result(code, message, null);
    }

    public static <T> R<T> fail(BizCode bizCode) {
        return result(bizCode.getNum(), bizCode.getMessage(), null);
    }


    public static <T> R<T> fail(BizException e) {
        return result(e.getCode(), e.getMessage(), null);
    }

    // ===== 核心构建 =====
    private static <T> R<T> result(BizCode bizCode, T data) {
        return result(bizCode.getNum(), bizCode.getMessage(), data);
    }

    private static <T> R<T> result(Integer code, String message, T data) {
        R<T> r = new R<>();
        r.setCode(code);
        r.setMessage(message);
        r.setData(data);
        return r;
    }
}