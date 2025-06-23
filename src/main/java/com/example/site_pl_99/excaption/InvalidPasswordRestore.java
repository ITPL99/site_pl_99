package com.example.site_pl_99.excaption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidPasswordRestore extends BaseException {
    public InvalidPasswordRestore(String message) {
        super(message);
    }
}
