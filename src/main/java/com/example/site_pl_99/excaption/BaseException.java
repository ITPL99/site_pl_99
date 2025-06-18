package com.example.site_pl_99.excaption;


// TODO: Настроить коды ответов на каждое исключение от 300 до 400 (Почитать про коды ответов сервера)
// Создать Перечисляемый тип в котором будут перечисление всех сообщений ошибок (Нужно для интернационализации)
public abstract class BaseException extends RuntimeException {
    public BaseException(String message) {
        super(message);
    }
}
