package com.example.site_pl_99.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.util.Arrays;

/**
 * Компонент для логирования запросов и вызовов сервисов через AOP (Aspect Oriented Programming)
 */
@Component
@Aspect // Обозначает, что этот класс — аспект (для внедрения логики в выполнение кода)
@Slf4j  // Ломбок автоматически добавляет логгер (log.info и т.д.)
public class LoggingAspect {

    // Pointcut для всех публичных методов в контроллерах
    @Pointcut("execution(public * com.example.site_pl_99.controller.*.*(..))")
    public void controllerLog() {
    }

    // Pointcut для всех методов в сервисах
    @Pointcut("execution(* com.example.site_pl_99.service.*.*(..))")
    public void service() {}

    // Выполняется перед выполнением метода контроллера
    @Before("controllerLog()")
    public void doBeforeController(JoinPoint jp) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if (attributes != null) {
            request = attributes.getRequest();
        }
        if (request != null) {
            // Логируем информацию о запросе
            log.info("NEW REQUEST: IP: {}, URL: {}, HTTP_METHOD: {}, CONTROLLER_METHOD: {}.{}",
                    request.getRemoteAddr(),                          // IP клиента
                    request.getRequestURL().toString(),              // URL запроса
                    request.getMethod(),                             // HTTP метод (GET, POST и т.д.)
                    jp.getSignature().getDeclaringTypeName(),        // Имя класса контроллера
                    jp.getSignature().getName());                    // Имя метода контроллера
        }
    }

    // Выполняется перед выполнением метода сервиса
    @Before("service()")
    public void doBeforeService(JoinPoint jp) {
        String className = jp.getSignature().getDeclaringTypeName();
        String methodName = jp.getSignature().getName();

        Object[] args = jp.getArgs();
        String argsString = args.length > 0 ? Arrays.toString(args) : "METHOD HAS NO ARGUMENTS";

        // Логируем вызов метода сервиса и его аргументы
        log.info("RUN SERVICE: SERVICE_METHOD: {}.{}. METHOD ARGUMENTS: [{}]",
                className, methodName, argsString);
    }

    // Выполняется после успешного возврата из метода контроллера
    @AfterReturning(returning = "returnObject", pointcut = "controllerLog()")
    public void doAfterReturning(Object returnObject) {
        // Логируем возвращаемое значение
        log.info("Return value: {}", returnObject);
    }

    // Выполняется после выполнения метода контроллера (в любом случае — даже если была ошибка)
    @After("controllerLog()")
    public void doAfter(JoinPoint jp) {
        // Логируем успешное выполнение метода
        log.info("Controller Method executed successfully: {}.{}",
                jp.getSignature().getDeclaringTypeName(),
                jp.getSignature().getName());
    }

    // Измеряет время выполнения метода контроллера
    @Around("controllerLog()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();     // Старт времени
        Object proceed = joinPoint.proceed();        // Выполняем метод
        long executionTime = System.currentTimeMillis() - start; // Считаем разницу

        // Логируем время выполнения
        log.info("Execution method: {}.{}. Execution time: {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                executionTime);

        return proceed;
    }

    // Выполняется при возникновении исключения в методе контроллера
    @AfterThrowing(throwing = "ex", pointcut = "controllerLog()")
    public void throwsException(JoinPoint jp, Exception ex) {
        String methodName = jp.getSignature().getName();
        String className = jp.getTarget().getClass().getSimpleName();

        // Логируем ошибку и её сообщение
        log.error("Exception in {}.{} with arguments {}. Exception message: {}",
                className, methodName, Arrays.toString(jp.getArgs()), ex.getMessage());
    }
}
