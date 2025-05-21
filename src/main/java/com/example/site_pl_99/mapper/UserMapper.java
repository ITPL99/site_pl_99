package com.example.site_pl_99.mapper;

import com.example.site_pl_99.dto.UserDTORequestRegister;
import com.example.site_pl_99.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User userMapper(UserDTORequestRegister userDTORequestRegister){
        return new User()
                .setEmail(userDTORequestRegister.getEmail())
                .setPassword(userDTORequestRegister.getPassword())
                .setLogin(userDTORequestRegister.getLogin());
    }
}
