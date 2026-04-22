package com.example.site_pl_99.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Slf4j
@Component
public class Internalization {
    private final MessageSource messageSource;

    @Autowired
    public Internalization(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String key, Locale lang){
        log.info("Получение сообщения по ключу {} ", key);
        log.info("Получение сообщения по локале {}", lang);
        String message = messageSource.getMessage(key, null, lang);
        log.info("Полученно сообщение от интернационализации: {}", message);
        return message;
    }
}
