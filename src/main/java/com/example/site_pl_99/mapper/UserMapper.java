package com.example.site_pl_99.mapper;

import com.example.site_pl_99.dto.RoleDTOResponse;
import com.example.site_pl_99.dto.UserDtoRequestRegister;
import com.example.site_pl_99.dto.UserDtoResponse;
import com.example.site_pl_99.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserEntity toUserEntity(UserDtoRequestRegister userDtoRequestRegister) {
        return new UserEntity().setUsername(userDtoRequestRegister.getUsername())
                .setPassword(userDtoRequestRegister.getPassword())
                .setMail(userDtoRequestRegister.getMail());
    }

    public static UserDtoResponse toUserDtoResponse(UserEntity userEntity) {
        return new UserDtoResponse().setId(userEntity.getId())
                .setUsername(userEntity.getUsername())
                .setMail(userEntity.getMail())
                .setRoles(userEntity.getRoleEntityList().stream().map(roleEntity -> new RoleDTOResponse().setTitle(roleEntity.getTitle())).toList());
    }

    public static List<UserDtoResponse> toUserDtoResponseList(List<UserEntity> userEntityList) {
        return userEntityList.stream().map(UserMapper::toUserDtoResponse).collect(Collectors.toList());
    }
}
