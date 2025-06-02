package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.UserDtoRequestRegister;
import com.example.site_pl_99.dto.UserDtoResponse;
import com.example.site_pl_99.entity.RoleEntity;
import com.example.site_pl_99.excaption.BaseException;
import com.example.site_pl_99.mapper.UserMapper;
import com.example.site_pl_99.service.UserService;
import com.example.site_pl_99.utils.Internalization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Locale;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Пользователь")
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @Operation(
            summary = "Вернуть список всех пользователей",
            description = "Возвращает список всех зарегистрированных пользователей и принимает язык через хедер, включая их Id, имена и роли."
    )
    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() throws BaseException {
            return ResponseEntity.ok(UserMapper.toUserDtoResponseList(userService.getAll()));
    }
    @Operation(
            summary = "Добавить нового пользователя",
            description = "Добавляет нового пользователя: требует имя, пароль и роль и принимает язык через хедер."
    )
    @PostMapping("/add")
    public ResponseEntity<?> addNewUser(@RequestBody UserDtoRequestRegister userDtoRequestRegister) throws BaseException {
            return ResponseEntity.ok(UserMapper.toUserDtoResponse(userService.save(userDtoRequestRegister)));
    }
    @Operation(
            summary = "Вернуть пользователя по логину",
            description = "Возвращает информацию о пользователе по указанному логину(имени пользователя) и принимает язык через хедер"
    )
    @PostMapping("/get-login")
    public ResponseEntity<?> getUserByLogin(@RequestParam String username) throws BaseException {
            return ResponseEntity.ok(UserMapper.toUserDtoResponse(userService.getByUsername(username)));
    }

    @GetMapping("/get-all-by-create-time")
    public ResponseEntity<?> getUsersByCreateTime(@RequestParam LocalDateTime createTime) throws BaseException {
            return ResponseEntity.ok(userService.getAllByCreateTime(createTime));
    }

    @GetMapping("/get-all-by-update-time")
    public ResponseEntity<?> getUsersByUpdatedTime(@RequestParam LocalDateTime updateTime) throws BaseException {
            return ResponseEntity.ok(UserMapper.toUserDtoResponseList(userService.getAllByUpdatedTime(updateTime)));
    }

    @GetMapping("/get-all-by-roles")
    public ResponseEntity<?> getUsersByRoles(@RequestParam String role) throws BaseException {
            return ResponseEntity.ok(UserMapper.toUserDtoResponseList(userService.getAllByUserRole(new RoleEntity().setTitle(role))));
    }
}
