package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.entity.UserEntity;
import com.example.site_pl_99.enums.Active;
import com.example.site_pl_99.excaption.UserNotFoundException;
import com.example.site_pl_99.repository.RoleRepository;
import com.example.site_pl_99.repository.UserRepository;
import com.example.site_pl_99.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserEntity getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("error.userNotFound"));
    }

    @Override
    public UserEntity getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("error.userNotFound"));
    }

    @Override
    public UserEntity save(UserEntity entity) {
        return userRepository.save(entity);
    }

    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("error.userNotFound"));
        userEntity.setActive(Active.DELETED);
        userRepository.save(userEntity);
    }

//    @Override
//    public List<UserEntity> getAllByCreateTime(OffsetDateTime createTime) {
//        return userRepository.findByDateCreated(createTime).orElseThrow(()-> new UserNotFoundException("error.userNotFound"));
//    }
//
//    @Override
//    public List<UserEntity> getAllByUpdatedTime(OffsetDateTime updateTime) {
//        return userRepository.findByDateUpdated(updateTime).orElseThrow(()-> new UserNotFoundException("error.userNotFound"));
//    }

//    @Override
//    public List<UserEntity> getAllByUserRole(RoleEntity role) {
//        return userRepository.findAllByRoleEntityList(role).orElseThrow(()-> new UserNotFoundException("error.findUserByRole"));
//    }


}
