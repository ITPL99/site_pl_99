package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.UserDtoResponse;
import com.example.site_pl_99.excaption.BaseException;
import com.example.site_pl_99.mapper.UserMapper;
import com.example.site_pl_99.service.AuthService;
import com.example.site_pl_99.utils.Internalization;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

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
            description = "Принимает имя и пароль пользователя и принимает язык через хедер, что бы войти в аккаунт"
    )
    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password
            ) throws BaseException {
            return authService.login(username, password);
    }
    @Operation(
            summary = "Получить текущего пользователя",
            description = "Возвращает информацию о текущем пользователе, включая его id, имя и роль. "
    )
    @GetMapping("/current")
    public UserDtoResponse getCurrentAuthUser(){
        return UserMapper.toUserDtoResponse(authService.getCurrentUser());
    }
}
