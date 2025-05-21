package com.example.site_pl_99.dto;

import com.example.site_pl_99.entity.Role;

import java.util.List;

public class UserDTO {
    private Long id;
    private String login;
    private String email;
    private List<Role> role;

    public UserDTO() {
    }

    public UserDTO(Long id,
                   String login,
                   String email,
                   List<Role> role) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public UserDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public UserDTO setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public List<Role> getRole() {
        return role;
    }

    public UserDTO setRole(List<Role> role) {
        this.role = role;
        return this;
    }
}
