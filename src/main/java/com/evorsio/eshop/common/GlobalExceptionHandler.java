package com.evorsio.eshop.common;

import cn.dev33.satoken.exception.NotLoginException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Evorsio
 * @since 2026/6/16
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BizException.class)
    public R<BizException> handleBizException(BizException e) {
        log.warn("业务异常: {}", e.getMessage());
        return R.fail(e);
    }

    @ExceptionHandler(BindException.class)
    public R<BizException> handleBindException(BindException e) {
        String msg = e.getBindingResult().getFieldErrors()
                .stream().map(f -> f.getField() + ": " + f.getDefaultMessage())
                .findFirst()
                .orElse(BizCode.PARAM_ERROR.getMessage());
        log.warn("参数异常: {}", e.getMessage());
        return R.fail(BizCode.PARAM_ERROR.getNum(), msg);
    }

    @ExceptionHandler(NotLoginException.class)
    public R<Void> handleNotLoginException(NotLoginException e) {
        log.warn("未登录异常: {}", e.getMessage());
        return R.fail(BizCode.NOT_LOGIN);
    }

    @ExceptionHandler(Exception.class)
    public R<Void> handleException(Exception e) {
        log.error("系统异常", e);
        return R.fail();
    }
}
