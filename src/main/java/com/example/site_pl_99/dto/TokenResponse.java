package com.example.site_pl_99.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO для ответа с токенами аутентификации.
 * <p>
 * Содержит access token и refresh token с информацией о типе токенов и времени их истечения.
 * Используется при успешной аутентификации пользователя.
 * </p>
 *
 * @author PL99 Team
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Ответ с токенами аутентификации (Access Token и Refresh Token)")
public class TokenResponse {

    /**
     * Access Token - JWT токен для доступа к защищенным ресурсам.
     * Передается в заголовке Authorization: Bearer {accessToken}
     * Время жизни: 24 часа
     */
    @Schema(
            description = """
                    Access Token (JWT) для авторизации запросов к API.
                    
                    Передается в заголовке: **Authorization: Bearer {accessToken}**
                    
                    **Характеристики:**
                    - Тип: JWT (JSON Web Token)
                    - Содержит: userId, username, roles
                    - Используется для: доступа к защищенным endpoint'ам
                    
                    **Пример декодированного токена:**
                    ```json
                    {
                      "id": 1,
                      "username": "admin",
                      "type": "ACCESS",
                      "sub": "admin",
                      "iat": 1713690000,
                      "exp": 1713776400
                    }
                    ```
                    """,
            example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String accessToken;

    /**
     * Refresh Token - строка для получения нового Access Token.
     * Хранится в базе данных, используется только для обновления access token.
     * Время жизни: 7 дней
     */
    @Schema(
            description = """
                    Refresh Token для получения нового Access Token без повторной аутентификации.
                    
                    **Характеристики:**
                    - Тип: UUID строка
                    - Хранение: база данных (таблица refresh_tokens)
                    - Используется для: endpoint **POST /api/auth/refresh**
                    
                    **Важно:**
                    - Не передавайте refresh token в Authorization заголовке
                    - Отправляйте только в теле запроса на /api/auth/refresh
                    - При компрометации токена - срочно измените пароль
                    
                    **Пример:**
                    ```
                    POST /api/auth/refresh
                    {
                      "refreshToken": "550e8400-e29b-41d4-a716-446655440000"
                    }
                    ```
                    """,
            example = "550e8400-e29b-41d4-a716-446655440000",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String refreshToken;

    /**
     * Тип токена (всегда "Bearer")
     */
    @Schema(
            description = """
                    Тип токена для заголовка Authorization.
                    
                    Значение всегда **Bearer**.
                    
                    **Использование:**
                    ```
                    Authorization: Bearer {accessToken}
                    ```
                    """,
            example = "Bearer",
            defaultValue = "Bearer",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String tokenType;

    /**
     * Информация о пользователе для фронтенда
     */
    @Schema(
            description = "Информация о аутентифицированном пользователе",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private UserInfo user;

    /**
     * Статус операции
     */
    @Schema(
            description = "Статус аутентификации",
            example = "SUCCESS",
            allowableValues = {"SUCCESS", "FAILED"},
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String status;

    /**
     * Сообщение для пользователя
     */
    @Schema(
            description = """
                    Информационное сообщение о результате операции.
                    
                    **Примеры:**
                    - "Аутентификация успешна"
                    - "Неверный логин или пароль"
                    """,
            example = "Аутентификация успешна"
    )
    private String message;

    /**
     * Внутренний класс с информацией о пользователе
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "Информация о пользователе для клиента")
    public static class UserInfo {

        @Schema(
                description = "ID пользователя для локального хранения и кэширования",
                example = "1",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        private Long id;

        @Schema(
                description = "Имя пользователя для отображения в интерфейсе",
                example = "admin",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        private String username;

        @Schema(
                description = "Список ролей пользователя для проверки прав на фронтенде",
                example = "[\"ADMIN\", \"USER\"]",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        private List<String> roles;
    }
}
