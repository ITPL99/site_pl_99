package com.example.site_pl_99.security;


import com.example.site_pl_99.entity.UserEntity;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
public class JWTHandler {

    @Value("${jwt.secret.key}")
    private String secret;
    
    @Value("${jwt.access.token.time}")
    private Long accessTokenExpire;
    
    @Value("${jwt.refresh.token.time}")
    private Long refreshTokenExpire;

    private SecretKey secretKey;

    private SecretKey getSecretKey() {
        if(secretKey == null) {
            byte[] byteSecretKey = Decoders.BASE64.decode(secret);
            secretKey = Keys.hmacShaKeyFor(byteSecretKey);
        }
        return secretKey;
    }


    /**
     * Генерирует Access Token (JWT) с информацией о пользователе.
     * Время жизни: 24 часа (86400000 мс)
     *
     * @param userDetails информация о пользователе
     * @return строка JWT access token
     */
    public String generateAccessToken(UserDetails userDetails){
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + accessTokenExpire);
        Map<String, Object> claims = new HashMap<>();
        UserEntity user = (UserEntity) userDetails;
        claims.put("id", user.getId());
        claims.put("username", user.getUsername());
        claims.put("type", "ACCESS");

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(userDetails.getUsername())
                .issuedAt(now)
                .expiration(expireDate)
                .and()
                .signWith(getSecretKey())
                .compact();
    }
    
    /**
     * Генерирует Refresh Token (UUID строка).
     * Время жизни хранится в базе данных: 7 дней (604800000 мс)
     * Refresh token используется только для получения нового access token
     *
     * @return строка refresh token (UUID)
     */
    public String generateRefreshToken(){
        return UUID.randomUUID().toString();
    }

    public String extractUsernameFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }


    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token);
            return true;
        } catch (ExpiredJwtException ex) {
            log.info("------->>>>>>  {}: {}", ex.getClass().getSimpleName(), ex.getMessage());
        } catch (MalformedJwtException ex) {
            log.info("------->>>>>>  {}: {}", ex.getClass().getSimpleName(), ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            log.info("------->>>>>>  {}: {}", ex.getClass().getSimpleName(), ex.getMessage());
        } catch (IllegalArgumentException ex) {
            log.info("------->>>>>>  {}: {}", ex.getClass().getSimpleName(), ex.getMessage());
        } catch (SignatureException ex) {
            log.info("------->>>>>>  {}: {}", ex.getClass().getSimpleName(), ex.getMessage());
        } catch (Exception ex) {
            log.info("------->>>>>>  {}: {}", ex.getClass().getSimpleName(), ex.getMessage());
            throw ex;
        }
        return false;
    }
}
