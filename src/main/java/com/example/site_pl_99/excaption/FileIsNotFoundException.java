package com.example.site_pl_99.excaption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileIsNotFoundException extends BaseException {
    public FileIsNotFoundException(String message) {
        super(message);
    }
}
