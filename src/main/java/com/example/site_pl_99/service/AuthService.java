package com.example.site_pl_99.service;

import com.example.site_pl_99.dto.UserDtoResponse;
import com.example.site_pl_99.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {

     UserEntity getCurrentUser();

     String login(String username, String password);
     String logout();
}
