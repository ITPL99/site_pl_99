package com.example.site_pl_99.aspect;

import com.example.site_pl_99.excaption.BaseException;
import com.example.site_pl_99.utils.Internalization;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

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
        return ResponseEntity.badRequest().body(internalization.getMessage(ex.getMessage(), locale));
    }
}
