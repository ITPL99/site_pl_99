package com.example.site_pl_99.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component

public class LogAspectService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.example.site_pl_99.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Вход в метод: {}", joinPoint.getSignature().getName());
    }

    @AfterReturning("execution(* com.example.site_pl_99.service.*.*(..))")
    public void slogAfter(JoinPoint joinPoint) {
        logger.info("Выход из метода: {}", joinPoint.getSignature().getName());
    }

    @AfterThrowing(pointcut = "execution(* com.example.site_pl_99.service.*.*(..))", throwing = "ex")
    public void logException(JoinPoint joinPoint, Throwable ex) {
        logger.error("Ошибка в методе: {} — {}", joinPoint.getSignature().getName(), ex.getMessage());
    }
}