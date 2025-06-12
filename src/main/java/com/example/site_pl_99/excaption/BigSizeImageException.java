package com.example.site_pl_99.excaption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
public class BigSizeImageException extends BaseException {
    public BigSizeImageException(String message) {
        super(message);
    }
}
