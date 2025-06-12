package com.example.site_pl_99.dto;

import com.example.site_pl_99.entity.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;


import java.time.LocalDateTime;
import java.util.List;
@Schema(description = "DTO для предоставления информаций о пользователе")
@RequiredArgsConstructor
public class UserDtoResponse {
    @Schema(description = "Уникальный идентификатор пользователя(ставится автоматически сервером)", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    @Schema(description = "имя пользователя", example = "Доктурбаев Нурсултан")
    private String username;
    @Schema(description = "электроная почта пользователя",example = "abc@gmail.com")
    private String mail;
    @Schema(description = "Список ролей пользователя в системе(админ, гость, юзер)")
    private List<RoleDTOResponse> roles;
    @Schema(description = "Дата создания аккаунта пользователя(Ставится автоматически сервером)", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime dateCreated;
    @Schema(description = "Дата последнего обновления информацйи о пользователе(Ставится автоматически сервером)", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime dateUpdated;

    public Long getId() {
        return id;
    }

    public UserDtoResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDtoResponse setUsername(String username) {
        this.username = username;
        return this;
    }

    public List<RoleDTOResponse> getRoles() {
        return roles;
    }

    public UserDtoResponse setRoles(List<RoleDTOResponse> roles) {
        this.roles = roles;
        return this;
    }

    public String getMail() {
        return mail;
    }

    public UserDtoResponse setMail(String mail) {
        this.mail = mail;
        return this;
    }


}
