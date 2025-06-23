package com.example.site_pl_99.excaption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoSuchMessageStatusException extends RuntimeException {
    public NoSuchMessageStatusException(String message) {
        super(message);
    }
}
