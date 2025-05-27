package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.UserDtoRequestRegister;
import com.example.site_pl_99.dto.UserDtoResponse;
import com.example.site_pl_99.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
            description = "Возвращает список всех зарегистрированных пользователей, включая их Id, имена и роли."
    )
    @GetMapping("/all")
    public List<UserDtoResponse> getAllUsers() {
        return userService.getAll().stream().map(UserDtoResponse::new).toList();
    }
    @Operation(
            summary = "Добавить нового пользователя",
            description = "Добавляет нового пользователя: требует имя, пароль и роль."
    )
    @PostMapping("/add")
    public UserDtoResponse addNewUser(@RequestBody UserDtoRequestRegister userDtoRequestRegister) {
        return new UserDtoResponse( userService.save(userDtoRequestRegister));
    }
    @Operation(
            summary = "Вернуть пользователя по логину",
            description = "Возвращает информацию о пользователе по указанному логину(имени пользователя) "
    )
    @PostMapping("/get-login")
    public UserDtoResponse getUserByLogin(@RequestParam String username){
        return new UserDtoResponse(userService.getByUsername(username));
    }

    
}
