package com.example.springbootdemo.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    private static void loggerRequestMappingAnnotation() {
    }

    @Pointcut("@within(org.springframework.stereotype.Service)")
    private static void loggerServiceAnnotation() {
    }

    @Before("loggerServiceAnnotation()")
    public void before(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getMethod().getName();
        String logMsg = "Log created before calling {} method with Service annotation";
        log.info(logMsg, methodName);
        log.info(logMsg, methodName);
    }

    @AfterReturning("loggerServiceAnnotation()")
    public void after(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getMethod().getName();
        String logMsg = "Log created after calling {} method with Service annotation";
        log.info(logMsg, methodName);
        log.info(logMsg, methodName);
    }

    @AfterThrowing(value = "loggerServiceAnnotation()", throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Throwable exception) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getMethod().getName();
        String logMsg = "Exception occurred: In method: {}, Exception message is {}";
        log.error(logMsg, methodName, exception.getMessage());
    }

    @Before("loggerRequestMappingAnnotation()")
    public void logBefore() {
        log.info("Log created before calling method with RequestMapping");
    }

    @After("loggerRequestMappingAnnotation()")
    public void logAfter() {
        log.info("Log created after calling method with RequestMapping");
    }
}
