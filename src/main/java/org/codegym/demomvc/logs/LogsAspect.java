package org.codegym.demomvc.logs;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogsAspect {

    @Before("execution(* org.codegym.demomvc.controller.*.*(..))")
    public void logControllerMethodCalls() {
        System.out.println("Executing a controller method...");
    }

    @After("execution(* org.codegym.demomvc.controller.*.*(..))")
    public void logControllerMethodExecutionTime() {
        System.out.println("Controller method execution time: " + (System.currentTimeMillis() - System.nanoTime()) / 1000000 + " ms");
    }

    @Before("execution(* org.codegym.demomvc.service.*.*(..))")
    public void logServiceMethodCalls() {
        System.out.println("Executing a service method...");
    }
}
