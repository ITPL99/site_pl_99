package com.example.site_pl_99.dto;

public class RoleDTO {
    private String role;

    public RoleDTO() {
    }

    public RoleDTO(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public RoleDTO setRole(String role) {
        this.role = role;
        return this;
    }
}
