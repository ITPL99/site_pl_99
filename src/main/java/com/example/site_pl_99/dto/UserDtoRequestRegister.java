package com.example.site_pl_99.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Данные для регистрации нового пользователя")
public class UserDtoRequestRegister {

    @Schema(description = "Имя пользователя", example = "john_doe", required = true)
    private String username;

    @Schema(description = "Пароль пользователя", example = "P@ssw0rd!", required = true)
    private String password;

    @Schema(description = "Email пользователя", example = "john.doe@example.com", required = true)
    private String email;
    public String getUsername() {
        return username;
    }

    public UserDtoRequestRegister setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDtoRequestRegister setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDtoRequestRegister setEmail(String email) {
        this.email = email;
        return this;
    }
}
