package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.dto.RefreshTokenRequest;
import com.example.site_pl_99.dto.TokenResponse;
import com.example.site_pl_99.entity.RefreshToken;
import com.example.site_pl_99.entity.UserEntity;
import com.example.site_pl_99.enums.Active;
import com.example.site_pl_99.excaption.AuthorizeException;
import com.example.site_pl_99.excaption.InvalidPasswordRestore;
import com.example.site_pl_99.excaption.NotImplementedException;
import com.example.site_pl_99.repository.RefreshTokenRepository;
import com.example.site_pl_99.repository.UserRepository;
import com.example.site_pl_99.security.JWTHandler;
import com.example.site_pl_99.service.AuthService;
import com.example.site_pl_99.service.MailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AuthServiceImpl.class);
    
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JWTHandler jwtHandler;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;
    
    @Value("${jwt.refresh.token.time}")
    private Long refreshTokenDurationMs;

    public AuthServiceImpl(
            UserRepository userRepository,
            RefreshTokenRepository refreshTokenRepository,
            JWTHandler jwtHandler,
            PasswordEncoder passwordEncoder, 
            MailService mailService
    ) {
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtHandler = jwtHandler;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return  userRepository.findByUsername(username).orElseThrow(()-> new AuthorizeException("error.authorization"));
    }

    @Override
    public UserEntity getCurrentUser() {
        return (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    @Transactional
    public TokenResponse login(String username, String password) {
        log.info("------>>>>> Пришел логин {}", username);
        UserEntity authUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new AuthorizeException("error.authorization"));
        
        if (!passwordEncoder.matches(password, authUser.getPassword())) {
            throw new AuthorizeException("error.authorization");
        }
        
        // Удаляем старый refresh token если есть
        refreshTokenRepository.findByUser(authUser).ifPresent(refreshTokenRepository::delete);
        
        // Генерируем новую пару токенов
        String accessToken = jwtHandler.generateAccessToken(authUser);
        String refreshToken = createRefreshToken(authUser);
        
        // Создаем UserInfo для ответа
        TokenResponse.UserInfo userInfo = TokenResponse.UserInfo.builder()
                .id(authUser.getId())
                .username(authUser.getUsername())
                .roles(authUser.getAuthorities().stream()
                        .map(auth -> auth.getAuthority().replace("ROLE_", ""))
                        .toList())
                .build();
        
        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType("Bearer")
                .user(userInfo)
                .status("SUCCESS")
                .message("Аутентификация успешна")
                .build();
    }
    
    @Override
    @Transactional
    public TokenResponse refreshToken(RefreshTokenRequest request) {
        log.info("------>>>>> Запрос на обновление токена");
        
        // Ищем refresh token в базе
        RefreshToken refreshToken = refreshTokenRepository.findByToken(request.getRefreshToken())
                .orElseThrow(() -> new AuthorizeException("error.invalidRefreshToken"));
        
        // Проверяем не истек ли срок
        if (refreshToken.isExpired()) {
            refreshTokenRepository.delete(refreshToken);
            throw new AuthorizeException("error.refreshTokenExpired");
        }
        
        UserEntity user = refreshToken.getUser();
        
        // Удаляем старый refresh token
        refreshTokenRepository.delete(refreshToken);
        
        // Генерируем новую пару токенов
        String newAccessToken = jwtHandler.generateAccessToken(user);
        String newRefreshToken = createRefreshToken(user);
        
        // Создаем UserInfo для ответа
        TokenResponse.UserInfo userInfo = TokenResponse.UserInfo.builder()
                .id(user.getId())
                .username(user.getUsername())
                .roles(user.getAuthorities().stream()
                        .map(auth -> auth.getAuthority().replace("ROLE_", ""))
                        .toList())
                .build();
        
        return TokenResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .tokenType("Bearer")
                .user(userInfo)
                .status("SUCCESS")
                .message("Токены успешно обновлены")
                .build();
    }
    
    /**
     * Создает и сохраняет refresh token для пользователя
     */
    private String createRefreshToken(UserEntity user) {
        RefreshToken refreshToken = RefreshToken.builder()
                .token(jwtHandler.generateRefreshToken())
                .user(user)
                .expiryDate(Instant.now().plusMillis(refreshTokenDurationMs))
                .build();
        
        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken.getToken();
    }

    @Override
    public String logout() {
        throw new NotImplementedException();
    }
    
    @Override
    @Transactional
    public void logoutUser(String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AuthorizeException("error.authorization"));
        refreshTokenRepository.deleteByUser(user);
        log.info("------>>>>> Пользователь {} вышел из системы, refresh токены удалены", username);
    }

    @Override
    public void passwordRestoration(String emailOrLogin) {
        log.info("----.>>>>>> {} ", emailOrLogin);
        UserEntity user = userRepository.findByUsername(emailOrLogin).orElse( null);
        if(Objects.isNull(user)) {
             user = userRepository.findByEmail(emailOrLogin).orElseThrow(() -> new InvalidPasswordRestore("Неверные данные логина или почты"));
        }
        user.setActive(Active.UPDATED);
        user.setActiveCode(UUID.randomUUID().toString());
        String titleMessage = LocaleContextHolder.getLocale().getLanguage().equals("ru")?"Восстановление пароля для сайта pl99.kg":
                LocaleContextHolder.getLocale().getLanguage().equals("kg")?"PL99.kg веб-сайты үчүн сырсөздү калыбына келтирүү":
                        "Password recovery for PL99.KG website";

        String message = LocaleContextHolder.getLocale().getLanguage().equals("ru")?"Для восстановления пароля пройдите по данной ссылке http://195.38.165.33:8080/api/auth/update-password/"+ user.getActiveCode():
                LocaleContextHolder.getLocale().getLanguage().equals("kg")?"Сырсөздү калыбына келтирүү үчүн, ушул шилтемеге өтүңүз http://195.38.165.33:8080/api/auth/update-password/"+ user.getActiveCode():
                        "To restore the password, go to this link http://195.38.165.33:8080/api/auth/update-password/"+ user.getActiveCode();
        userRepository.save(user);
        mailService.sendMessageTo(user.getEmail(), titleMessage,message);
    }

    @Override
    public String updatePassword(String activeCode, String newPassword) {
        UserEntity user = userRepository.findByActiveCode(activeCode).orElseThrow(()-> new InvalidPasswordRestore("error.invalidPasswordRestore"));
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setActiveCode(null);
        user.setActive(Active.ACTIVE);
        return "Пароль успешно изменен у пользователя под логином " + userRepository.save(user).getUsername();
    }
}
