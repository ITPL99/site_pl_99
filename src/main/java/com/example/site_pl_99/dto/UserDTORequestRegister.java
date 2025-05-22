package com.example.site_pl_99.dto;

import java.util.List;

public class UserDTORequestRegister {
    private String login;
    private String password;
    private String email;
    private List<String> role;

    public UserDTORequestRegister() {
    }

    public String getUsername() {
        return username;
    }

    public UserEntity toEntity(){
        return new UserEntity()
                .setUsername(username)
                .setPassword(password);
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
}
