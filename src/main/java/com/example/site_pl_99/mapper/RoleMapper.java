package com.example.site_pl_99.mapper;

import com.example.site_pl_99.dto.RoleDTOResponse;
import com.example.site_pl_99.entity.RoleEntity;

public class RoleMapper {
    public static RoleDTOResponse roleDTOResponse(RoleEntity roleEntity) {
        return new RoleDTOResponse().setTitle(roleEntity.getTitle());
    }
}
