package com.evorsio.eshop.common;

import lombok.Getter;

/**
 * @author Evorsio
 * @since 2026/6/16
 * 业务异常
 */
@Getter
public class BizException extends RuntimeException {
    private final Integer code;
    private final String message;

    public BizException(BizCode bizCode) {
        this.code = bizCode.getNum();
        this.message = bizCode.getMessage();
    }
}
