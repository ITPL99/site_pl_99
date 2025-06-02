package com.example.site_pl_99.aspects;

import com.example.site_pl_99.excaption.BaseException;
import com.example.site_pl_99.utils.Internalization;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class ExceptionHandlerController {
    private final Internalization internalization;

    @Autowired
    public ExceptionHandlerController(Internalization internalization) {
        this.internalization = internalization;
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<?> manageException(BaseException e, HttpServletRequest request) {
        Locale language = request.getLocale();
        return ResponseEntity.badRequest().body(internalization.getMessage(e.getMessage(), language));
    }
}
