package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.dto.UserDTORequestRegister;
import com.example.site_pl_99.entity.Role;
import com.example.site_pl_99.entity.User;
import com.example.site_pl_99.mapper.UserMapper;
import com.example.site_pl_99.repository.RoleRepository;
import com.example.site_pl_99.repository.UserRepository;
import com.example.site_pl_99.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
    }

    @Override
    public User getByLogin(String login) {
        return userRepository.findUserByLogin(login).orElseThrow(() -> new RuntimeException("Такого пользователя нет"));
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow( () -> new RuntimeException("Такого пользователя нет"));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(UserDTORequestRegister userDTORequestRegister) {
        User user = userMapper.userMapper(userDTORequestRegister);
        if(userDTORequestRegister.getRole().isEmpty()){
            user.setRole(List.of(
                    roleRepository.findByRole("USER").orElseThrow(() -> new RuntimeException("При регистрации произошла ошибка"))
                )
            );
        }else {
            List<Role> roles = new ArrayList<>();
            for(String element : userDTORequestRegister.getRole()){
                roles.add(roleRepository.findByRole(element).orElse(null));
            }
        }
        return userRepository.save(user);
    }
}
