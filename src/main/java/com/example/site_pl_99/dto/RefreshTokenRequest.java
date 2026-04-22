package com.example.site_pl_99.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO для запроса обновления Access Token.
 * <p>
 * Содержит refresh token, который используется для получения нового access token
 * без необходимости повторной аутентификации пользователя.
 * </p>
 *
 * @author PL99 Team
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Запрос на обновление Access Token с использованием Refresh Token")
public class RefreshTokenRequest {

    /**
     * Refresh Token - UUID строка, полученная при первичной аутентификации.
     * Должен быть действительным (не просроченным) и существовать в базе данных.
     */
    @NotBlank(message = "Refresh token не может быть пустым")
    @Schema(
            description = """
                    Refresh Token для получения нового Access Token.
                    
                    **Требования:**
                    - Должен быть получен при предыдущей аутентификации
                    - Не должен быть просрочен (время жизни 7 дней)
                    - Должен существовать в базе данных
                    
                    **Формат:** UUID строка
                    **Пример:** 550e8400-e29b-41d4-a716-446655440000
                    
                    **Важно:**
                    - При успешном обновлении выдается новая пара токенов
                    - Старый refresh token становится недействительным
                    - Новый refresh token также живет 7 дней
                    
                    **Пример запроса:**
                    ```json
                    {
                      "refreshToken": "550e8400-e29b-41d4-a716-446655440000"
                    }
                    ```
                    """,
            example = "550e8400-e29b-41d4-a716-446655440000",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minLength = 36,
            maxLength = 36
    )
    private String refreshToken;
}
