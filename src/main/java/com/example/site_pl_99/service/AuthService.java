package com.example.site_pl_99.service;

import com.example.site_pl_99.dto.RefreshTokenRequest;
import com.example.site_pl_99.dto.TokenResponse;
import com.example.site_pl_99.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {

     UserEntity getCurrentUser();

     /**
      * Аутентификация пользователя с выдачей пары токенов (access + refresh).
      *
      * @param username имя пользователя
      * @param password пароль
      * @return TokenResponse с access token и refresh token
      */
     TokenResponse login(String username, String password);

     /**
      * Обновление Access Token с использованием Refresh Token.
      *
      * @param request запрос с refresh token
      * @return TokenResponse с новой парой токенов
      */
     TokenResponse refreshToken(RefreshTokenRequest request);

     String logout();

    void passwordRestoration(String emailOrLogin);

    String updatePassword(String activeCode, String newPassword);

    /**
     * Выйти из системы, удалив все refresh токены пользователя.
     *
     * @param username имя пользователя
     */
    void logoutUser(String username);
}
