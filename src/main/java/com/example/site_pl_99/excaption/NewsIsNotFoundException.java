package com.example.site_pl_99.excaption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NewsIsNotFoundException extends RuntimeException {
    public NewsIsNotFoundException(String message) {
        super(message);
    }
}
