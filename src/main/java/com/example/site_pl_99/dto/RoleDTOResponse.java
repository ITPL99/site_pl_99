package com.example.site_pl_99.dto;

import com.example.site_pl_99.entity.RoleEntity;

public class RoleDTOResponse {
    private String title;

    public String getTitle() {
        return title;
    }

    public RoleDTOResponse setTitle(String title) {
        this.title = title;
        return this;
    }
}
