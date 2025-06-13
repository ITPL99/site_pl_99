package com.example.site_pl_99.excaption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MessageIsNotFound extends BaseException {
    public MessageIsNotFound(String message) {
        super(message);
    }
}
