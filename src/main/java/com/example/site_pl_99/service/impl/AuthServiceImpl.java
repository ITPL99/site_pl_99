package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.excaption.AuthorizeException;
import com.example.site_pl_99.repository.UserRepository;
import com.example.site_pl_99.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("Что то пришло: " + username );
        return  userRepository.findByUsername(username).orElseThrow(()-> new AuthorizeException("Неверный логин или пароль"));
    }
}
