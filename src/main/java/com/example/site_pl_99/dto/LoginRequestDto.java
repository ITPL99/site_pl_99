package com.example.site_pl_99.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO для запроса аутентификации (логин).
 * <p>
 * Содержит учетные данные пользователя для проверки и получения токенов.
 * Использует Bean Validation для проверки входных данных на уровне контроллера.
 * </p>
 *
 * @author PL99 Team
 * @since 1.0
 */
@Schema(description = "Запрос на аутентификацию пользователя")
public class LoginRequestDto {

    /**
     * Имя пользователя для аутентификации
     */
    @NotBlank(message = "Username is required")
    @Size(min = 3, message = "Username must be at least 3 characters")
    @Schema(
            description = "Имя пользователя для аутентификации",
            example = "admin",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minLength = 3
    )
    private String username;

    /**
     * Пароль пользователя
     */
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    @Schema(
            description = "Пароль пользователя",
            example = "qwe123",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minLength = 6,
            format = "password"
    )
    private String password;

    public LoginRequestDto() {
    }

    public LoginRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
