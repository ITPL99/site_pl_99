package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.UserDtoResponse;
import com.example.site_pl_99.entity.UserEntity;
import com.example.site_pl_99.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
@Tag(name = "Аутентификация")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @Operation(
            summary = "Вход в аккаунт",
            description = "Принимает имя и пароль пользователя, что бы войти в аккаунт"
    )
    @PostMapping("/login")
    public String login(
            @RequestParam String username,
           @RequestParam String password
    ) {
        return authService.login(username,password);
    }
    @Operation(
            summary = "Получить текущего пользователя",
            description = "Возвращает информацию о текущем пользователе, включая его id, имя и роль. "
    )
    @GetMapping("/current")
    public UserDtoResponse getCurrentAuthUser(){
        return authService.getCurrentUser();
    }
}
