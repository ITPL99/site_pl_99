package com.example.site_pl_99.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Аспект логирования для контроллеров.
 * Логирует вход, выход и ошибки всех методов внутри пакета controller.
 */
@Slf4j                  // Автоматически подключает логгер log.*
@Aspect                // Указывает, что класс является AOP аспектом
@Component             // Spring-компонент для автоматического сканирования
public class LogAspectControler {

    // Выполняется перед каждым методом в контроллерах
    @Before("execution(* com.example.site_pl_99.controller.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        // Логирует начало работы метода
        log.info("------------------------>>>>> Вход в метод: {}", joinPoint.getSignature().getName());
    }

    // Выполняется после успешного выполнения метода
    @AfterReturning("execution(* com.example.site_pl_99.controller.*.*(..))")
    public void slogAfter(JoinPoint joinPoint) {
        // Логирует выход из метода
        log.info("----------------->>>>>    Выход из метода: {}", joinPoint.getSignature().getName());
    }

    // Выполняется, если метод контроллера выбрасывает исключение
    @AfterThrowing(pointcut = "execution(* com.example.site_pl_99.controller.*.*(..))", throwing = "ex")
    public void logException(JoinPoint joinPoint, Throwable ex) {
        // Логирует ошибку и её сообщение
        log.error("--------------------------->>>>>>>  Ошибка в методе: {} — {}", joinPoint.getSignature().getName(), ex.getMessage());
    }
}
