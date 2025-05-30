package com.example.site_pl_99.dto;

import com.example.site_pl_99.entity.UserEntity;
import lombok.RequiredArgsConstructor;


import java.time.LocalDateTime;
import java.util.List;
@RequiredArgsConstructor
public class UserDtoResponse {
    private Long id;
    private String username;
    private String mail;
    private List<RoleDTOResponse> roles;
    private LocalDateTime dateCreated;
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
