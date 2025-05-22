package com.example.site_pl_99.dto;

import java.util.List;

public class UserDTORequestRegister {
    private String login;
    private String password;
    private String email;
    private List<String> role;

    public UserDTORequestRegister() {
    }

    public UserDTORequestRegister(String login,
                                  String password,
                                  String email,
                                  List<String> role) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public UserDTORequestRegister setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDTORequestRegister setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDTORequestRegister setEmail(String email) {
        this.email = email;
        return this;
    }

    public List<String> getRole() {
        return role;
    }

    public UserDtoRequestRegister setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }
}
