package com.example.site_pl_99.exception;

public class UserNotFoundException extends ApplicationBaseException {
    public UserNotFoundException(String message) {
        super(message);
    }
    //пользатель таким id и именем не найден
}
