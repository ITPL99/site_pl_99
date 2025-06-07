package com.example.site_pl_99.excaption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class AvatarIsAlreadyExistsException extends BaseException {
    public AvatarIsAlreadyExistsException(String message) {
        super(message);
    }
}
