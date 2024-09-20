package org.codegym.demomvc.logs;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    // Pointcut để áp dụng cho tất cả các phương thức trong package controller
    @Before("execution(* org.codegym.demomvc.controller.*.*(..))")
    public void logBefore() {
        System.out.println("A request is being handled...");
    }

    @After("execution(* org.codegym.demomvc.controller.*.*(..))")
    public void logAfter() {
        System.out.println("Request has been handled.");
    }

    // @Around để log trước và sau khi phương thức controller được gọi
    @Around("execution(* org.codegym.demomvc.controller.*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Before method: " + joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();  // Gọi phương thức thực tế trong controller
        System.out.println("After method: " + joinPoint.getSignature().getName());
        return result;
    }
}
