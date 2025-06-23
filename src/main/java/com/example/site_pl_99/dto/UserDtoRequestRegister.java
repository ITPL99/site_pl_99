package com.example.site_pl_99.dto;

public class UserDtoRequestRegister {
    private String username;
    private String password;
    private String email;

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

    public String getEmail() {
        return email;
    }

    public UserDtoRequestRegister setEmail(String email) {
        this.email = email;
        return this;
    }
}
