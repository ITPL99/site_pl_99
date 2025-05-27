package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.UserDtoResponse;
import com.example.site_pl_99.entity.UserEntity;
import com.example.site_pl_99.excaption.BaseException;
import com.example.site_pl_99.service.AuthService;
import com.example.site_pl_99.utils.Internalization;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final Internalization internalization;

    public AuthController(AuthService authService, Internalization internalization) {
        this.authService = authService;
        this.internalization = internalization;
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            @RequestHeader(name = "Accept-Language", required = false)Locale locale
            ) {
        try{
            return authService.login(username, password);
        }catch (BaseException e){
            return internalization.getMessage(e.getMessage(), locale);
        }
    }

    @GetMapping("/current")
    public UserDtoResponse getCurrentAuthUser(){
        return authService.getCurrentUser();
    }
}
