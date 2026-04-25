package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.ApiResponseWrapper;
import com.example.site_pl_99.dto.LoginRequestDto;
import com.example.site_pl_99.dto.RefreshTokenRequest;
import com.example.site_pl_99.dto.TokenResponse;
import com.example.site_pl_99.dto.UserDtoResponse;
import com.example.site_pl_99.excaption.BaseException;
import com.example.site_pl_99.mapper.UserMapper;
import com.example.site_pl_99.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

/**
 * Контроллер для управления аутентификацией и авторизацией пользователей.
 * <p>
 * Предоставляет endpoints для входа в систему, обновления токенов,
 * восстановления пароля и получения информации о текущем пользователе.
 * </p>
 *
 * @author PL99 Team
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/auth")
@Tag(
        name = "Аутентификация",
        description = """
                API для управления аутентификацией пользователей. </br>
                Для разработки в системе существуют пользователи с логинами (admin, testuser, moderator) у всех пароль qwe123 
                
                **Основные возможности:**
                - Вход в систему (login) - получение пары токенов
                - Обновление Access Token (refresh) - без повторного ввода пароля
                - Восстановление пароля (password-restoration)
                - Получение информации о текущем пользователе (current)
                
                **Жизненный цикл токенов:**
                - Access Token: 24 часа (используется в Authorization: Bearer)
                - Refresh Token: 7 дней (используется только для получения нового access token)
                
                **Безопасность:**
                - Все endpoints (кроме login и refresh) требуют валидный Access Token
                - Refresh Token можно использовать только один раз (ротация)
                - При смене пароля все сессии завершаются
                """
)
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @Operation(
            summary = "Вход в аккаунт",
            description = """
                    **Аутентификация пользователя и получение токенов доступа.**
                    
                    После успешной аутентификации возвращается пара токенов:
                    - **Access Token** (JWT) - для авторизации запросов, время жизни 24 часа
                    - **Refresh Token** (UUID) - для обновления access token, время жизни 7 дней
                    
                    **Использование Access Token:**
                    ```
                    Authorization: Bearer {accessToken}
                    ```
                    
                    **Использование Refresh Token:**
                    Отправьте refresh token в теле запроса на endpoint **POST /api/auth/refresh**
                    
                    **Пример успешного ответа:**
                    ```json
                    {
                      "accessToken": "eyJhbGciOiJIUzI1NiIs...",
                      "refreshToken": "550e8400-e29b-41d4-a716-446655440000",
                      "tokenType": "Bearer",
                      "accessTokenExpiry": "2026-04-22T12:30:00Z",
                      "refreshTokenExpiry": "2026-04-28T12:30:00Z",
                      "accessTokenExpiresIn": 86400,
                      "refreshTokenExpiresIn": 604800,
                      "status": "SUCCESS",
                      "message": "Аутентификация успешна"
                    }
                    ```
                    
                    **Ошибки:**
                    - 401 - Неверный логин или пароль
                    - 400 - Пустые параметры запроса
                    """
    )
    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "200",
                    description = "Аутентификация успешна",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TokenResponse.class),
                            examples = @ExampleObject(
                                    name = "Успешный ответ",
                                    value = """
                                            {
                                              "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
                                              "refreshToken": "550e8400-e29b-41d4-a716-446655440000",
                                              "tokenType": "Bearer",
                                              "user": {
                                                "id": 1,
                                                "username": "admin",
                                                "roles": ["ADMIN"]
                                              },
                                              "status": "SUCCESS",
                                              "message": "Аутентификация успешна"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Неверный логин или пароль",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{\"error\": \"error.authorization\", \"message\": \"Неверный логин или пароль\"}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Ошибки валидации (невалидные данные)",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class)
                    )
            )
    })
    @PostMapping("/login")
    public TokenResponse login(
            @Valid @RequestBody LoginRequestDto loginRequest
    ) throws BaseException {
        log.info("----->>>>>  получили запрос на вход: {}", loginRequest.getUsername());
        return authService.login(loginRequest.getUsername(), loginRequest.getPassword());
    }

    /**
     * Обновление Access Token с использованием Refresh Token.
     * 
     * @param request запрос с refresh token
     * @return новая пара токенов
     */
    @Operation(
            summary = "Обновление Access Token (Refresh Token)",
            description = """
                    **Получение новой пары токенов без повторного ввода пароля.**
                    
                    Используйте этот endpoint, когда Access Token истекает (через 24 часа),
                    чтобы получить новую пару токенов без повторного ввода пароля.
                    
                    **Важно:**
                    - Refresh Token можно использовать **только один раз** (ротация)
                    - При успешном обновлении выдается **новая пара** токенов
                    - Старый Refresh Token становится недействительным
                    - Новый Refresh Token также живет 7 дней от момента выдачи
                    
                    **Когда использовать:**
                    - Access Token истек (401 ошибка на защищенных endpoints)
                    - Пользователь активно использует приложение более 24 часов
                    
                    **Пример запроса:**
                    ```json
                    {
                      "refreshToken": "550e8400-e29b-41d4-a716-446655440000"
                    }
                    ```
                    
                    **Пример успешного ответа:**
                    ```json
                    {
                      "accessToken": "eyJhbGciOiJIUzI1NiIs...",
                      "refreshToken": "660f9511-f3ac-52e5-b827-557766551111",
                      "tokenType": "Bearer",
                      "accessTokenExpiry": "2026-04-23T12:30:00Z",
                      "refreshTokenExpiry": "2026-04-29T12:30:00Z",
                      "accessTokenExpiresIn": 86400,
                      "refreshTokenExpiresIn": 604800,
                      "status": "SUCCESS",
                      "message": "Токены успешно обновлены"
                    }
                    ```
                    
                    **Ошибки:**
                    - 401 - Невалидный или истекший refresh token
                    - 400 - Пустой refresh token
                    
                    **Рекомендации:**
                    - Храните refresh token в secure httpOnly cookie или secure storage
                    - Не передавайте refresh token в URL параметрах
                    - Автоматически обновляйте токены до истечения срока
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Токены успешно обновлены",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TokenResponse.class),
                            examples = @ExampleObject(
                                    name = "Успешное обновление",
                                    value = """
                                            {
                                              "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
                                              "refreshToken": "660f9511-f3ac-52e5-b827-557766551111",
                                              "tokenType": "Bearer",
                                              "accessTokenExpiry": "2026-04-23T12:30:00Z",
                                              "refreshTokenExpiry": "2026-04-29T12:30:00Z",
                                              "accessTokenExpiresIn": 86400,
                                              "refreshTokenExpiresIn": 604800,
                                              "status": "SUCCESS",
                                              "message": "Токены успешно обновлены"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Невалидный или истекший refresh token",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{\"error\": \"error.invalidRefreshToken\", \"message\": \"Невалидный refresh token\"}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Пустой refresh token"
            )
    })
    @PostMapping("/refresh")
    public TokenResponse refreshToken(
            @Valid
            @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Запрос с refresh token",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RefreshTokenRequest.class),
                            examples = @ExampleObject(
                                    name = "Пример запроса",
                                    value = "{\"refreshToken\": \"550e8400-e29b-41d4-a716-446655440000\"}"
                            )
                    )
            )
            RefreshTokenRequest request
    ) {
        log.info("----->>>>>  получили запрос на обновление токена");
        return authService.refreshToken(request);
    }

    @Operation(
            summary = "Получить текущего пользователя",
            description = """
                    Возвращает информацию о текущем аутентифицированном пользователе.
                    
                    **Требуется:** Валидный Access Token в заголовке Authorization.
                    """
    )
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Информация о пользователе получена"),
            @ApiResponse(responseCode = "401", description = "Отсутствует или невалидный токен")
    })
    @GetMapping("/current")
    public UserDtoResponse getCurrentAuthUser(){
        return UserMapper.toUserDtoResponse(authService.getCurrentUser());
    }

    @Operation(
            summary = "Инициализация изменения пароля",
            description = """
                    В случае утери пароля или активации нового аккаунта пользователя 
                    отправляется письмо на почту с секретным кодом для восстановления пароля.
                    
                    Принимает логин пользователя или email, по которому его можно найти в системе.
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Письмо отправлено"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @PostMapping("/password-restoration")
    public String passwordRestoration(
            @Parameter(description = "Логин или email пользователя", example = "admin")
            @RequestParam String emailOrLogin
    ){
        authService.passwordRestoration(emailOrLogin);
        Locale local = LocaleContextHolder.getLocale();
        return local.getLanguage().equals("ru") ? "Вам на почту отправлен секретный код для восстановления пароля" :
                local.getLanguage().equals("kg") ? "Сырсөздү калыбына келтирүү үчүн жашыруун код сизге почта аркылуу жөнөтүлдү" :
                        "A secret code to restore password has been sent to you by mail";
    }

    @Operation(
            summary = "Изменение пароля пользователя",
            description = """
                    Изменение пароля с использованием секретного кода, полученного по email.
                    
                    Секретный код одноразовый. После успешного изменения пароля 
                    все существующие сессии (refresh токены) удаляются.
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пароль успешно изменен"),
            @ApiResponse(responseCode = "400", description = "Невалидный или истекший код")
    })
    @PostMapping("/update-password/{active_code}")
    public String updatePassword(
            @Parameter(description = "Секретный код для изменения пароля", example = "abc123")
            @PathVariable("active_code") String activeCode,
            
            @Parameter(description = "Новый пароль", example = "newPassword123")
            @RequestParam String newPassword
    ){
       return authService.updatePassword(activeCode, newPassword);
    }
}

