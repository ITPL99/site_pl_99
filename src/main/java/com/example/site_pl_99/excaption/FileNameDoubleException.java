package com.example.site_pl_99.excaption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class FileNameDoubleException extends BaseException {
    public FileNameDoubleException(String message) {
        super(message);
    }
}
