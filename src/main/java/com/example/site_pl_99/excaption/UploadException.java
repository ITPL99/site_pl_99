package com.example.site_pl_99.excaption;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
public class UploadException extends BaseException{
    public UploadException(String message) {
        super(message);
    }
}
