package com.example.site_pl_99.excaption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class SqlException extends BaseException {
    public SqlException(String message) {
        super(message);
    }
}
//ошибка в дата базе или типа того
