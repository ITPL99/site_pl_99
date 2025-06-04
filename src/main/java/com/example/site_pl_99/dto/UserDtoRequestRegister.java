package com.example.site_pl_99.dto;

import com.example.site_pl_99.entity.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;

import java.util.List;
@Schema(description = "DTO для запроса с информаций о пользователе при регистраций")
@RequiredArgsConstructor
public class UserDtoRequestRegister {
    @Schema(description = "Имя пользователя (полное ФИО)",example = "Лола Бакытовна")
    private String username;
    @Schema(description = "Пароль пользователя",example = "123qwerty")
    private String password;
    @Schema(description = "Почта пользователя",example = "licey99@gmail.com")
    private String mail;
    @Schema(description = "Список ролей(админ, гость, юзер)")
    private List<String> roles;

    public String getUsername() {
        return username;
    }

    public UserDtoRequestRegister setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDtoRequestRegister setPassword(String password) {
        this.password = password;
        return this;
    }

    public List<String> getRoles() {
        return roles;
    }

    public UserDtoRequestRegister setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }

    public String getMail() {
        return mail;
    }

    public UserDtoRequestRegister setMail(String mail) {
        this.mail = mail;
        return this;
    }
}
