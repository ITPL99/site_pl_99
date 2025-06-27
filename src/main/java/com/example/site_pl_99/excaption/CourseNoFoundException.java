package com.example.site_pl_99.excaption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CourseNoFoundException extends BaseException {
    public CourseNoFoundException(String message) {
        super(message);
    }
}
