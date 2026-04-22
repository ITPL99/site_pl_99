package com.example.site_pl_99.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Аспект логирования для контроллеров.
 * Логирует вход, выход и ошибки всех методов внутри пакета controller.
 */
@Slf4j                  // Автоматически подключает логгер log.*
@Aspect                // Указывает, что класс является AOP аспектом
@Component             // Spring-компонент для автоматического сканирования
public class LogAspectControler {



    @Pointcut(" within(@org.springframework.stereotype.Service *) ||" +
            " within(@org.springframework.web.bind.annotation.RestController *)")
    private void publicMethodsFromLoggingPackageController() {
    }


    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Логгер на проверку данных входящих в метод.
     * Данная проверка логгера отрабатывает перед тем как
     * отработает ключевой метод
     */
    @Before("publicMethodsFromLoggingPackageController()")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getDeclaringType().getSimpleName() + "." + 
                         joinPoint.getSignature().getName();
        String currentUser = getCurrentUsername();
        String args = formatArgs(joinPoint.getArgs());

        log.info("\n\n---> Вход: {} | \nПользователь: {} | \nАргументы: {}\n\n", methodName, currentUser, args);
    }

    // Выполняется после успешного выполнения метода
    @AfterReturning(pointcut = "publicMethodsFromLoggingPackageController()", returning = "result")
    public void slogAfter(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getDeclaringType().getSimpleName() + "." + 
                         joinPoint.getSignature().getName();
        String resultStr = formatResult(result);

        log.info("\n\n<--- Выход: {} | \nРезультат: {} \n\n", methodName, resultStr);
    }

    // Выполняется, если метод контроллера выбрасывает исключение
    @AfterThrowing(pointcut = "publicMethodsFromLoggingPackageController()", throwing = "ex")
    public void logException(JoinPoint joinPoint, Throwable ex) {
        String methodName = joinPoint.getSignature().getDeclaringType().getSimpleName() + "." + 
                         joinPoint.getSignature().getName();
        String currentUser = getCurrentUsername();
        String args = formatArgs(joinPoint.getArgs());

        log.error("\n\n<!--- Ошибка: {} | \nПользователь: {} | \nАргументы: {} | \nОшибка: {}   \n\n",
                  methodName, currentUser, args, ex.getMessage(), ex);
    }

    /**
     * Получает имя текущего авторизованного пользователя
     */
    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            return "anonymous";
        }
        return authentication.getName();
    }

    /**
     * Форматирует аргументы для логирования
     */
    private String formatArgs(Object[] args) {
        if (args == null || args.length == 0) {
            return "[]";
        }
        try {
            // Фильтруем sensitive данные (пароли)
            Object[] filteredArgs = Arrays.stream(args)
                    .map(arg -> {
                        if (arg instanceof String && 
                            (arg.toString().toLowerCase().contains("password") || 
                             arg.toString().toLowerCase().contains("pass"))) {
                            return "***HIDDEN***";
                        }
                        return arg;
                    })
                    .toArray();
            return objectMapper.writeValueAsString(filteredArgs);
        } catch (Exception e) {
            return Arrays.toString(args);
        }
    }

    /**
     * Форматирует результат для логирования
     */
    private String formatResult(Object result) {
        if (result == null) {
            return "null";
        }
        try {
            String json = objectMapper.writeValueAsString(result);
            // Ограничиваем длину для больших ответов
            if (json.length() > 1000) {
                return json.substring(0, 1000) + "... (truncated, total: " + json.length() + " chars)";
            }
            return json;
        } catch (Exception e) {
            return result.toString();
        }
    }
}
