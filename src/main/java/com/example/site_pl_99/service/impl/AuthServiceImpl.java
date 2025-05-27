package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.dto.UserDtoResponse;
import com.example.site_pl_99.entity.UserEntity;
import com.example.site_pl_99.excaption.AuthorizeException;
import com.example.site_pl_99.excaption.NotImplementedException;
import com.example.site_pl_99.repository.UserRepository;
import com.example.site_pl_99.security.JWTHandler;
import com.example.site_pl_99.security.PasswordEncoderImpl;
import com.example.site_pl_99.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);
    private final UserRepository userRepository;
    private final JWTHandler jwtHandler;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(
            UserRepository userRepository,
            JWTHandler jwtHandler,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.jwtHandler = jwtHandler;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return  userRepository.findByUsername(username).orElseThrow(()-> new AuthorizeException("Неверный логин или пароль"));
    }

    @Override
    public UserDtoResponse getCurrentUser() {
        return new UserDtoResponse((UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    @Override
    public String login(String username, String password) {
        log.info("------>>>>> Пришел логин {}", username);
        log.info("------>>>>> Пришел пароль {}", password);
        UserEntity authUser = userRepository.findByUsername(username).orElseThrow(()-> new AuthorizeException("Неверный логин или пароль"));
        log.info("------>>>>> Пришел пароль пользователя  {}", authUser.getPassword());
        if(!passwordEncoder.matches(password, authUser.getPassword())) {
            throw new AuthorizeException("Неверный логин или пароль");
        }
        return jwtHandler.jwtGenerator(authUser);
    }

    @Override
    public String logout() {
        throw new NotImplementedException();
    }
}
