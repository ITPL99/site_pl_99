package com.example.site_pl_99.excaption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncorrectInputException extends BaseException {
    public IncorrectInputException(String message) {
        super(message);
    }
}
//если пользователь не правильно ввел данные