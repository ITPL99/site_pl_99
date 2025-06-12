package com.example.site_pl_99.dto;

import com.example.site_pl_99.entity.RoleEntity;
import io.swagger.v3.oas.annotations.Hidden;

@Hidden
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
