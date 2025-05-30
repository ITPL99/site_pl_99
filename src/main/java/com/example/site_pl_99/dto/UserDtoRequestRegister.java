package com.example.site_pl_99.dto;

import com.example.site_pl_99.entity.UserEntity;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserDtoRequestRegister {
    private String username;
    private String password;
    private String mail;
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
