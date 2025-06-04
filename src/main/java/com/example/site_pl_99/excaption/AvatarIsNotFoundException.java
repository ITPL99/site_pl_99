package com.example.site_pl_99.excaption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AvatarIsNotFoundException extends BaseException {
    public AvatarIsNotFoundException(String message) {
        super(message);
    }
}
