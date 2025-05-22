package com.example.site_pl_99.exception;

public class DuplicateUserException extends ApplicationBaseException{
    public DuplicateUserException(String message) {
        super(message);
    }
    //используется если пользователь с таким именем/почтой уже существует
}
