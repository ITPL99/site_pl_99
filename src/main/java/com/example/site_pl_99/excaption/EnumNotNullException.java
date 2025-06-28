package com.example.site_pl_99.excaption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EnumNotNullException extends BaseException {
    public EnumNotNullException(String message) {
        super(message);
    }
}
