package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.UserDtoResponse;
import com.example.site_pl_99.excaption.BaseException;
import com.example.site_pl_99.mapper.UserMapper;
import com.example.site_pl_99.service.AuthService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Locale;

@Slf4j
@Tag(name = "Аутентификация")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @Operation(
            summary = "Вход в аккаунт",
            description = "Принимает имя и пароль пользователя и принимает язык через хедер, что бы войти в аккаунт"
    )
    @ApiResponses(
            {@ApiResponse(responseCode = "400",description = "некорректный ввод"),
            @ApiResponse(responseCode = "200", description = "Успешно выполнено"),
            @ApiResponse(responseCode = "409",description = "Ресурс уже существует и не может быть дубликатов")})
    @PostMapping("/login")
    public String login(@Parameter(description = "Имя пользователя")
            @RequestParam String username,
            @Parameter(description = "Пароль пользователя")
            @RequestParam String password
            ) throws BaseException {
        System.out.println("userName: "  + username + " Password: " + password);
        log.info("----->>>>>  получили запрос в систему ");
            return authService.login(username, password);
    }

    @Operation(
            summary = "Получить текущего пользователя",
            description = "Возвращает информацию о текущем пользователе, включая его id, имя и роль. "
    )
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/current")
    public UserDtoResponse getCurrentAuthUser(){
        return UserMapper.toUserDtoResponse(authService.getCurrentUser());
    }


    @Operation(
            summary = "Инициализация изменения пароля (Надо изменить и договорится с фронтами на какой адрес перенаправлять)",
            description = """
                    В случае утери пароля или активации нового аккаунта пользователя нужно восстановить пароль на почту пользователю,
                    должно прилететь письмо с указание на какую точку доступа ему нужно обратиться где сразу передается секретный код активации изменения пароля.
                    Принимает в себя параметр логина пользователя или его почты по которому его можно найти в системе и корректно изменить пароль
                    """
    )
    @PostMapping("/password-restoration")
    public String passwordRestoration(@RequestParam String emailOrLogin){
        authService.passwordRestoration(emailOrLogin);
        Locale local = LocaleContextHolder.getLocale();
        return local.getLanguage().equals("ru")? "Вам на почту отправлен секретный код для восстановления пароля":
                local.getLanguage().equals("kg")? "Сырсөздү калыбына келтирүү үчүн жашыруун код сизге почта аркылуу жөнөтүлдү":
                        "A secret code to restore password has been sent to you by mail";
    }

    @Operation(
            summary = "Изменение пароля пользователя.",
            description = """
                    передаются два параметра это секретный код для изменения пароля (он одноразовый) и 
                    новый пароль пользователя который пожелал пароль изменить.
                    Секретный пароль принимается в качестве переменной запроса.
                    Пароль принимается в качестве параметра запроса
                    """
    )
    @PostMapping("/update-password/{active_code}")
    public String updatePassword(
            @PathVariable("active_code") String activeCode,
            @RequestParam String newPassword
    ){
       return authService.updatePassword(activeCode, newPassword);
    }
}
