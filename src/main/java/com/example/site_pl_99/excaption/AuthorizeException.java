package com.example.site_pl_99.excaption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthorizeException extends BaseException{
    public AuthorizeException(String message) {
        super(message);
    }
}
