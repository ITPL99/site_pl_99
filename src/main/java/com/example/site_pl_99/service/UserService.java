package com.example.site_pl_99.service;


import com.example.site_pl_99.entity.UserEntity;


public interface UserService extends BaseService<UserEntity>{
    UserEntity getByUsername(String username);

}
