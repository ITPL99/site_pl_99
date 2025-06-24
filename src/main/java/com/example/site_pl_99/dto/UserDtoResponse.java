package com.example.site_pl_99.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;

@Schema(description = "Данные пользователя")
@RequiredArgsConstructor
public class UserDtoResponse {

    @Schema(description = "Уникальный идентификатор пользователя", example = "123")
    private Long id;

    @Schema(description = "Имя пользователя", example = "john_doe")
    private String username;

    @Schema(description = "Email пользователя", example = "john.doe@example.com")
    private String email;
    public Long getId() {
        return id;
    }

    public UserDtoResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDtoResponse setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDtoResponse setEmail(String email) {
        this.email = email;
        return this;
    }
}
