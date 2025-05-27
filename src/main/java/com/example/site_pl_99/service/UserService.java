package com.example.site_pl_99.service;

import com.example.site_pl_99.dto.UserDtoRequestRegister;
import com.example.site_pl_99.entity.RoleEntity;
import com.example.site_pl_99.entity.UserEntity;
import org.apache.catalina.User;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {

    UserEntity getByUsername(String username);
    UserEntity getById(Long id);
    List<UserEntity> getAll();
    List<UserEntity> getAllByCreateTime(LocalDateTime createTime);
    List<UserEntity> getAllByUpdatedTime(LocalDateTime updateTime);
    List<UserEntity> getAllByUserRole(RoleEntity role);
    UserEntity save(UserDtoRequestRegister newUser);
}
