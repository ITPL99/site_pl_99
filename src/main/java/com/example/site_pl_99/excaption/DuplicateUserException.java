package com.example.site_pl_99.excaption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateUserException extends BaseException {
    public DuplicateUserException(String message) {
        super(message);
    }
    //используется если пользователь с таким именем/почтой уже существует
}
