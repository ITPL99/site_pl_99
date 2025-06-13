package com.example.site_pl_99.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Аспект логирования для сервисного слоя.
 * Логирует вход, выход и ошибки всех методов внутри пакета service.
 */
@Slf4j                  // Ломбок добавляет логгер для log.info, log.error и т.д.
@Aspect                // Указывает, что это AOP аспект
@Component             // Делаем его компонентом Spring, чтобы он автоматически подхватился
public class LogAspectService {

    // Выполняется ПЕРЕД вызовом любого метода в пакете service
    @Before("execution(* com.example.site_pl_99.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("------------------------>>>>> Вход в метод: {}", joinPoint.getSignature().getName());
    }

    // Выполняется ПОСЛЕ УСПЕШНОГО выполнения метода в пакете service
    @AfterReturning("execution(* com.example.site_pl_99.service.*.*(..))")
    public void slogAfter(JoinPoint joinPoint) {
        log.info("----------------->>>>>    Выход из метода: {}", joinPoint.getSignature().getName());
    }

    // Выполняется ЕСЛИ метод выбросил исключение
    @AfterThrowing(pointcut = "execution(* com.example.site_pl_99.service.*.*(..))", throwing = "ex")
    public void logException(JoinPoint joinPoint, Throwable ex) {
        log.error("--------------------------->>>>>>>  Ошибка в методе: {} — {}", joinPoint.getSignature().getName(), ex.getMessage());
    }
}
