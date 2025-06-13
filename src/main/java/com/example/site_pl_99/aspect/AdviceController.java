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
    @ExceptionHandler(AvatarIsAlreadyExistsException.class)
    public ResponseEntity<String> exceptionHandler(AvatarIsAlreadyExistsException ex, HttpServletRequest request) {
        Locale locale = request.getLocale();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(internalization.getMessage(ex.getMessage(), locale));
    }
    @ExceptionHandler(AvatarIsNotFoundException.class)
    public ResponseEntity<String> exceptionHandler(AvatarIsNotFoundException ex, HttpServletRequest request) {
        Locale locale = request.getLocale();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(internalization.getMessage(ex.getMessage(), locale));
    }
    @ExceptionHandler(BigSizeImageException.class)
    public ResponseEntity<String> exceptionHandler(BigSizeImageException ex, HttpServletRequest request) {
        Locale locale = request.getLocale();
        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(internalization.getMessage(ex.getMessage(), locale));
    }
    @ExceptionHandler(CourseIsNotFoundException.class)
    public ResponseEntity<String> exceptionHandler(CourseIsNotFoundException ex, HttpServletRequest request) {
        Locale locale = request.getLocale();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(internalization.getMessage(ex.getMessage(), locale));
    }
    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<String> exceptionHandler(DuplicateUserException ex, HttpServletRequest request) {
        Locale locale = request.getLocale();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(internalization.getMessage(ex.getMessage(), locale));
    }
    @ExceptionHandler(IncorrectInputException.class)
    public ResponseEntity<String> exceptionHandler(IncorrectInputException ex, HttpServletRequest request) {
        Locale locale = request.getLocale();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(internalization.getMessage(ex.getMessage(), locale));
    }
    @ExceptionHandler(MailIsNotFoundException.class)
    public ResponseEntity<String> exceptionHandler(MailIsNotFoundException ex, HttpServletRequest request){
        Locale locale = request.getLocale();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(internalization.getMessage(ex.getMessage(), locale));
    }
    @ExceptionHandler(MessageFailedToSendException.class)
    public ResponseEntity<String> exceptionHandler(MessageFailedToSendException ex, HttpServletRequest request){
        Locale locale = request.getLocale();
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(internalization.getMessage(ex.getMessage(), locale));
    }
    @ExceptionHandler(MessageIsNotFound.class)
    public ResponseEntity<String> exceptionHandler(MessageIsNotFound ex, HttpServletRequest request){
        Locale locale = request.getLocale();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(internalization.getMessage(ex.getMessage(), locale));
    }
    @ExceptionHandler(NewsIsNotFoundException.class)
    public ResponseEntity<String> exceptionHandler(NewsIsNotFoundException ex, HttpServletRequest request) {
        Locale locale = request.getLocale();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(internalization.getMessage(ex.getMessage(), locale));
    }
    @ExceptionHandler(NotEnoughLawException.class)
    public ResponseEntity<String> exceptionHandler(NotEnoughLawException ex, HttpServletRequest request) {
        Locale locale = request.getLocale();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(internalization.getMessage(ex.getMessage(), locale));
    }
    @ExceptionHandler(NotImplementedException.class)
    public ResponseEntity<String> exceptionHandler(NotImplementedException ex, HttpServletRequest request) {
        Locale locale = request.getLocale();
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(internalization.getMessage(ex.getMessage(), locale));
    }
    @ExceptionHandler(QualificationIsNotFoundException.class)
    public ResponseEntity<String> exceptionHandler(QualificationIsNotFoundException ex, HttpServletRequest request) {
        Locale locale = request.getLocale();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(internalization.getMessage(ex.getMessage(), locale));
    }
    @ExceptionHandler(SqlException.class)
    public ResponseEntity<String> exceptionHandler(SqlException ex, HttpServletRequest request) {
        Locale locale = request.getLocale();
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(internalization.getMessage(ex.getMessage(), locale));
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> exceptionHandler(UserNotFoundException ex, HttpServletRequest request) {
        Locale locale = request.getLocale();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(internalization.getMessage(ex.getMessage(), locale));
    }
    @ExceptionHandler(WorkerIsNotFoundException.class)
    public ResponseEntity<String> exceptionHandler(WorkerIsNotFoundException ex, HttpServletRequest request) {
        Locale locale = request.getLocale();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(internalization.getMessage(ex.getMessage(), locale));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception ex, HttpServletRequest request) {
        Locale locale = request.getLocale();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(internalization.getMessage(ex.getMessage(), locale));
    }
}
