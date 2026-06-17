package com.evorsio.eshop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author Evorsio
 * @since 2026/6/17
 */
@Slf4j
@Aspect
@Component
public class WebLogAspect {
    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void controllerLog() {}

    @Around("controllerLog()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        log.info("=====> 请求{}.{} 入参{}",className,methodName,args);

        try{
            Object result = joinPoint.proceed();
            long cost = System.currentTimeMillis() - start;
            log.info("<===== 响应{}.{} 返回{} 耗时{}ms",className,methodName,result,cost);
            return result;
        }catch (Throwable e){
            long cost = System.currentTimeMillis() -start;
            log.error("==X== 异常{}.{} 耗时{}ms 错误{}",className,methodName,cost,e.getMessage());
            throw e;
        }
    }
}
