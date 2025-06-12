package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.UserDtoRequestRegister;
import com.example.site_pl_99.dto.UserDtoResponse;
import com.example.site_pl_99.entity.RoleEntity;
import com.example.site_pl_99.excaption.BaseException;
import com.example.site_pl_99.mapper.UserMapper;
import com.example.site_pl_99.service.UserService;
import com.example.site_pl_99.utils.Internalization;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Locale;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Контроллер пользователя")
@RestController
@RequestMapping("/api/user")
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @Operation(
            summary = "Вернуть список всех пользователей",
            description = "Возвращает список всех зарегистрированных пользователей и принимает язык через хедер, включая их Id, имена и роли."
    )
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Список пользователей успешно возвращен")})
    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() throws BaseException {
            return ResponseEntity.ok(UserMapper.toUserDtoResponseList(userService.getAll()));
    }
    @Operation(
            summary = "Добавить нового пользователя",
            description = "Добавляет нового пользователя: требует имя, пароль и роль и принимает язык через хедер."
    )
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Успешно добавлен"),
    @ApiResponse(responseCode = "409",description = "Ресурс уже существует и не может быть дубликатов"),
    @ApiResponse(responseCode = "403", description = "Недостаточно прав"),
    @ApiResponse(responseCode = "400",description = "некоректный ввод")
    })
    @PostMapping("/add")
    public ResponseEntity<?> addNewUser(@Parameter(description = "Данные для регистраций") @RequestBody UserDtoRequestRegister userDtoRequestRegister) throws BaseException {
            return ResponseEntity.ok(UserMapper.toUserDtoResponse(userService.save(userDtoRequestRegister)));
    }
    @Operation(
            summary = "Вернуть пользователя по логину",
            description = "Возвращает информацию о пользователе по указанному логину(имени пользователя) и принимает язык через хедер"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно выполнено"),
            @ApiResponse(responseCode = "404", description = "Не найден"),
            @ApiResponse(responseCode = "400",description = "некоректный ввод")})
    @PostMapping("/get-login")
    public ResponseEntity<?> getUserByLogin(@Parameter(description = "Принимает имя пользователя")@RequestParam String username) throws BaseException {
            return ResponseEntity.ok(UserMapper.toUserDtoResponse(userService.getByUsername(username)));
    }
    @Operation(summary = "Вернут всех пользователей по дате создания",
    description = "веррнет список аккаунтов по указанному дате создания")
    @ApiResponses(value = {@ApiResponse(responseCode = "400",description = "некоректный ввод"),
            @ApiResponse(responseCode = "200", description = "Успешно выполнено")})
    @GetMapping("/get-all-by-create-time")
    public ResponseEntity<?> getUsersByCreateTime(@Parameter(description = "Дата создания")@RequestParam LocalDateTime createTime) throws BaseException {
            return ResponseEntity.ok(userService.getAllByCreateTime(createTime));
    }
    @Operation(summary = "Вернуть список аккаунтов по дате обнволения",
    description = "Возвращает список аккаунтов по веденному дате обновления")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Успешно выполнено"),@ApiResponse(responseCode = "400",description = "некоректный ввод")})

    @GetMapping("/get-all-by-update-time")
    public ResponseEntity<?> getUsersByUpdatedTime(@Parameter(description = "Дата обновления")@RequestParam LocalDateTime updateTime) throws BaseException {
            return ResponseEntity.ok(UserMapper.toUserDtoResponseList(userService.getAllByUpdatedTime(updateTime)));
    }
    @Operation(summary = "Вернуть список аккаунтов по ролям",
    description = "Возвращает список аккаунтов по ведденой роли")
    @ApiResponses(value = {@ApiResponse(responseCode = "400",description = "некоректный ввод"),
            @ApiResponse(responseCode = "200", description = "Успешно выполнено")})
    @GetMapping("/get-all-by-roles")
    public ResponseEntity<?> getUsersByRoles(@Parameter(description = "Роль")@RequestParam String role) throws BaseException {
            return ResponseEntity.ok(UserMapper.toUserDtoResponseList(userService.getAllByUserRole(new RoleEntity().setTitle(role))));
    }
}
