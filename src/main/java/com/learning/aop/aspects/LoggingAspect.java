package com.learning.aop.aspects;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {
    @Pointcut("execution(public * com.learning.aop.service.GreetService.*(..))")
    public void beforeMethodExecutionInGreet() {}


    @Around("beforeMethodExecutionInGreet()")
    public Object logFunction(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();

        String methodName = joinPoint.getSignature().getName();
        System.out.println(methodName + " is about to be called");
        Object result = joinPoint.proceed();
        System.out.println(methodName + " has been called");

        return result;
    }
}

