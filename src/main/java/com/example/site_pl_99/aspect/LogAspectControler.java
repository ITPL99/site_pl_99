package com.example.site_pl_99.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAspectControler {


    @Before("execution(* com.example.site_pl_99.controller.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("------------------------>>>>> Вход в метод: {}", joinPoint.getSignature().getName());
    }

    @AfterReturning("execution(* com.example.site_pl_99.controller.*.*(..))")
    public void slogAfter(JoinPoint joinPoint) {
        log.info("----------------->>>>>    Выход из метода: {}", joinPoint.getSignature().getName());
    }

    @AfterThrowing(pointcut = "execution(* com.example.site_pl_99.controller.*.*(..))", throwing = "ex")
    public void logException(JoinPoint joinPoint, Throwable ex) {
        log.error("--------------------------->>>>>>>  Ошибка в методе: {} — {}", joinPoint.getSignature().getName(), ex.getMessage());
    }
}