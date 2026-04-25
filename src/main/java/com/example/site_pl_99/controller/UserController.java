package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.UserDtoRequestRegister;
import com.example.site_pl_99.dto.UserDtoResponse;

import com.example.site_pl_99.excaption.BaseException;
import com.example.site_pl_99.mapper.UserMapper;
import com.example.site_pl_99.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;



import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Контроллер для управления пользователями.
 * <p>
 * Предоставляет API для управления пользователями системы.
 * </p>
 *
 * @author PL99 Team
 * @since 1.0
 */
@Tag(
        name = "Пользователи",
        description = """
                API для управления пользователями системы.
                
                **Возможности:**
                - Получение списка всех пользователей
                - Создание новых пользователей
                - Поиск пользователя по логину
                
                **Авторизация:** Все endpoints требуют Bearer токен
                """
)
@RestController
@RequestMapping("/api/user")
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @Operation(
            summary = "Получить список всех пользователей",
            description = """
                    Возвращает список всех зарегистрированных пользователей.
                    
                    **Включает:** ID, имя пользователя, email, роли, статус
                    
                    **Требуемая роль:** ADMIN
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Список пользователей успешно возвращен",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserDtoResponse.class))
                    )
            ),
            @ApiResponse(responseCode = "401", description = "Не авторизован"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав (требуется ADMIN)")
    })
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDtoResponse>> getAllUsers() throws BaseException {
            return ResponseEntity.ok(UserMapper.toUserDtoResponseList(userService.getAll()));
    }
    @Operation(
            summary = "Добавить нового пользователя",
            description = """
                    Создает нового пользователя в системе.
                    
                    **Обязательные поля:**
                    - username - уникальное имя пользователя
                    - password - пароль (минимум 8 символов)
                    - email - уникальный email
                    - roles - список ролей
                    
                    **Требуемая роль:** ADMIN
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешно добавлен",
                    content = @Content(schema = @Schema(implementation = UserDtoResponse.class))
            ),
            @ApiResponse(responseCode = "409", description = "Пользователь с таким логином или email уже существует"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав (требуется ADMIN)"),
            @ApiResponse(responseCode = "400", description = "Некорректный ввод данных")
    })
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDtoResponse> addNewUser(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные для регистрации пользователя",
                    required = true,
                    content = @Content(schema = @Schema(implementation = UserDtoRequestRegister.class))
            )
            @RequestBody UserDtoRequestRegister userDtoRequestRegister) throws BaseException {
            return ResponseEntity.ok(UserMapper.toUserDtoResponse(userService.save(UserMapper.toUserEntity(userDtoRequestRegister))));
    }
    @Operation(
            summary = "Получить пользователя по логину",
            description = """
                    Возвращает информацию о пользователе по указанному логину.
                    
                    **Включает:** ID, имя, email, роли, статус активности
                    """
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешно выполнено",
                    content = @Content(schema = @Schema(implementation = UserDtoResponse.class))
            ),
            @ApiResponse(responseCode = "401", description = "Не авторизован"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден"),
            @ApiResponse(responseCode = "400", description = "Некорректный ввод")
    })
    @GetMapping("/get-login")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDtoResponse> getUserByLogin(
            @Parameter(
                    description = "Имя пользователя (логин)",
                    example = "admin",
                    required = true
            )
            @RequestParam String username) throws BaseException {
            return ResponseEntity.ok(UserMapper.toUserDtoResponse(userService.getByUsername(username)));
    }

}
