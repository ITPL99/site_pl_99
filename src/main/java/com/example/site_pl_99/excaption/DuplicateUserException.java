package com.example.site_pl_99.excaption;

public class DuplicateUserException extends BaseException {
    public DuplicateUserException(String message) {
        super(message);
    }
    //используется если пользователь с таким именем/почтой уже существует
}
