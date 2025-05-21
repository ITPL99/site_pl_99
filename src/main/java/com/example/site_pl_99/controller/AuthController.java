package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.UserDtoResponse;
import com.example.site_pl_99.entity.UserEntity;
import com.example.site_pl_99.service.AuthService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
           @RequestParam String password
    ) {
        return authService.login(username,password);
    }

    @GetMapping("/current")
    public UserDtoResponse getCurrentAuthUser(){
        return getCurrentAuthUser();
    }
}
