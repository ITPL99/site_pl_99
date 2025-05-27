package com.example.site_pl_99.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class Internalization {
    private final MessageSource messageSource;

    @Autowired
    public Internalization(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String key, Locale lang){
        System.out.println(messageSource.getMessage(key, null, lang));
        return messageSource.getMessage(key, null, lang);
    }
}
