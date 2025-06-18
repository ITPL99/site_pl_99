package com.example.site_pl_99.dto;

public class UserDtoResponse {
    private Long id;
    private String username;
    private String email;

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

    public String getEmail() {
        return email;
    }

    public UserDtoResponse setEmail(String email) {
        this.email = email;
        return this;
    }
}
