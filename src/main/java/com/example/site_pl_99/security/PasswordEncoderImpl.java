package com.example.site_pl_99.security;


import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderImpl implements PasswordEncoder {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Environment env;


    public PasswordEncoderImpl(Environment environment) {
        this.env = environment;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
    }

    @Override
    public String encode(CharSequence rawPassword) {
//        if(env.matchesProfiles("test")) return rawPassword.toString();
        return this.bCryptPasswordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
//        if(env.matchesProfiles("test")) return rawPassword.toString().equals(encodedPassword);
        return this.bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }
}
