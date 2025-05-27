package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.dto.UserDtoRequestRegister;
import com.example.site_pl_99.entity.RoleEntity;
import com.example.site_pl_99.entity.UserEntity;
import com.example.site_pl_99.excaption.AuthorizeException;
import com.example.site_pl_99.excaption.UserNotFoundException;
import com.example.site_pl_99.repository.RoleRepository;
import com.example.site_pl_99.repository.UserRepository;
import com.example.site_pl_99.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("Такого пользователя не в системе"));
    }

    @Override
    public UserEntity getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Такого пользователя нет в системе"));
    }

    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    @Override
    public List<UserEntity> getAllByCreateTime(LocalDateTime createTime) {
        return userRepository.findByDateCreated(createTime).orElseThrow(()-> new UserNotFoundException("Пользователи не найдены по данной дате"));
    }

    @Override
    public List<UserEntity> getAllByUpdatedTime(LocalDateTime updateTime) {
        return userRepository.findByDateUpdated(updateTime).orElseThrow(()-> new UserNotFoundException("Пользователи не найдены по данной дате"));
    }

    @Override
    public List<UserEntity> getAllByUserRole(RoleEntity role) {
        return userRepository.findAllByRoleEntityList(role).orElseThrow(()-> new UserNotFoundException("Пользователи с такой ролью не найдены"));
    }

    @Override
    public UserEntity save(UserDtoRequestRegister newUser) {
        UserEntity userEntity = newUser.toEntity();
        if(newUser.getRoles() == null ||
                newUser.getRoles().isEmpty() ||
                (newUser.getRoles().size() == 1 &&
                        newUser.getRoles().get(0).isEmpty())) {
            userEntity.setRoleEntityList(List.of(
                    roleRepository.findByTitle("USER")
                            .orElseThrow(() -> new AuthorizeException("При регистрации произошла ошибка")))
            );
        }else {

            List<RoleEntity> roles = new ArrayList<>();
            for (String lineElementRoleList : newUser.getRoles()) {
                roles.add(roleRepository.findByTitle(lineElementRoleList).orElse(null));
            }
        }
       return userRepository.save(userEntity);
    }
}
