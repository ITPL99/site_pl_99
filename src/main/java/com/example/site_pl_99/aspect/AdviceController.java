package com.example.site_pl_99.aspect;

import com.example.site_pl_99.excaption.*;
import com.example.site_pl_99.utils.Internalization;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Locale;


// TODO: Настройть перехватчик ошибок (Почитать про AdviceControllerHandler)
@Hidden
@ControllerAdvice
public class AdviceController {
    private final Internalization internalization;

    @Autowired
    public AdviceController(Internalization internalization) {
        this.internalization = internalization;
    }
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<String> exceptionHandler(BaseException ex, HttpServletRequest request) {
        Locale locale = request.getLocale();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(internalization.getMessage(ex.getMessage(), locale));
    }
    @ExceptionHandler(AuthorizeException.class)
    public ResponseEntity<String> exceptionHandler(AuthorizeException ex, HttpServletRequest request) {
        Locale locale = request.getLocale();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(internalization.getMessage(ex.getMessage(), locale));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception ex, HttpServletRequest request) {
        Locale locale = request.getLocale();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(internalization.getMessage(ex.getMessage(), locale));
    }
}
