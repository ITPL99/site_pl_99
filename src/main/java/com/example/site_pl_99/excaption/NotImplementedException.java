package com.example.site_pl_99.excaption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class NotImplementedException extends BaseException {
    public NotImplementedException() {
        super("Этот функционал временно недоступен");
    }
}
