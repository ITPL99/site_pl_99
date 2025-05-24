package com.example.site_pl_99.dto;

import com.example.site_pl_99.entity.UserEntity;


import java.util.List;

public class UserDtoResponse {
    private Long id;
    private String username;
    private String email;
    private List<RoleDTOResponse> roles;

    public UserDtoResponse(UserEntity user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.roles = user.getRoleEntityList().stream().map(RoleDTOResponse::new).toList();
    }

    public String getEmail() {
        return email;
    }

    public UserDtoResponse setEmail(String email) {
        this.email = email;
        return this;
    }

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
}
