package com.example.site_pl_99.mapper;

import com.example.site_pl_99.dto.UserDtoRequest;
import com.example.site_pl_99.dto.UserDtoResponse;
import com.example.site_pl_99.entity.UserEntity;

import java.util.stream.Collectors;

public class UserMapper {
    public static UserEntity toEntity(UserDtoRequest request){
        return new UserEntity().setUsername(request.getUsername())
                .setPassword(request.getPassword())
                .setEmail(request.getEmail());
    }
    public static UserDtoResponse toResponse(UserEntity entity){
        return new UserDtoResponse().setUsername(entity.getUsername())
                .setUsername(entity.getUsername())
                .setEmail(entity.getEmail())
                .setRoles(entity.getRoles().stream().map(roleEntity -> roleEntity.getRoleName()).collect(Collectors.toList()));
    }

}
