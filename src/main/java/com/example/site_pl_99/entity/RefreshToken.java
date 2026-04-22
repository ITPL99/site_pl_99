package com.example.site_pl_99.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

/**
 * Сущность для хранения Refresh Token.
 * <p>
 * Refresh token используется для получения нового Access Token без повторной аутентификации пользователя.
 * Хранится в базе данных с указанием времени истечения срока действия (7 дней).
 * </p>
 *
 * @author PL99 Team
 * @see UserEntity
 * @since 1.0
 */
@Entity
@Table(name = "refresh_tokens",
        indexes = @Index(name = "idx_refresh_token", columnList = "token"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Сущность Refresh Token для продления сессии пользователя")
public class RefreshToken {

    /**
     * Уникальный идентификатор записи
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Уникальный идентификатор токена в базе данных",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    /**
     * Сам refresh token (UUID строка)
     */
    @Column(name = "token", nullable = false, unique = true, length = 255)
    @Schema(description = "Значение Refresh Token (UUID строка)",
            example = "550e8400-e29b-41d4-a716-446655440000",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String token;

    /**
     * Пользователь, которому принадлежит токен
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @Schema(description = "Пользователь, которому принадлежит токен",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private UserEntity user;

    /**
     * Дата и время истечения срока действия токена
     */
    @Column(name = "expiry_date", nullable = false)
    @Schema(description = "Дата и время истечения срока действия токена (UTC)",
            example = "2026-04-28T12:30:00Z",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private Instant expiryDate;

    /**
     * Дата и время создания токена
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    @Schema(description = "Дата и время создания токена",
            example = "2026-04-21T12:30:00Z",
            accessMode = Schema.AccessMode.READ_ONLY)
    private Instant createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = Instant.now();
    }

    /**
     * Проверяет, не истек ли срок действия токена
     *
     * @return true если токен просрочен, false если еще действителен
     */
    @Schema(description = "Проверка срока действия токена", hidden = true)
    public boolean isExpired() {
        return Instant.now().isAfter(this.expiryDate);
    }
}
