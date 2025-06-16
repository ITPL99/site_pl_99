package com.example.site_pl_99.dto;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserDtoRequest {
    private String username;
    private String password;
    private String email;
    private List<String> roles;

    public String getUsername() {
        return username;
    }

    public UserDtoRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDtoRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDtoRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public List<String> getRoles() {
        return roles;
    }

    public UserDtoRequest setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }
}
