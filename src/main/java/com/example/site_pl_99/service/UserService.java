package com.example.site_pl_99.service;

import com.example.site_pl_99.dto.UserDTORequestRegister;
import com.example.site_pl_99.entity.User;

import java.util.List;

public interface UserService {
    User getByLogin(String login);
    User getById(Long id);
    List<User> getAll();
    User save(UserDTORequestRegister newUser);
}
