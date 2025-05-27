package com.example.site_pl_99.excaption;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException(String message) {
        super(message);
    }
    //пользатель таким id и именем не найден
}
