package com.example.site_pl_99.repository;

import com.example.site_pl_99.entity.RefreshToken;
import com.example.site_pl_99.entity.UserEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

/**
 * Репозиторий для работы с Refresh Token.
 * <p>
 * Предоставляет методы для поиска, удаления и управления refresh токенами в базе данных.
 * </p>
 *
 * @author PL99 Team
 * @see RefreshToken
 * @since 1.0
 */
@Repository
@Tag(name = "Refresh Token Repository", description = "Операции с refresh токенами в базе данных")
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    /**
     * Находит refresh token по его значению.
     *
     * @param token строковое значение токена
     * @return Optional с найденным токеном или пустой Optional
     */
    @Operation(summary = "Найти токен по значению",
            description = "Поиск refresh token по его строковому значению")
    Optional<RefreshToken> findByToken(
            @Parameter(description = "Значение токена (UUID)") String token
    );

    /**
     * Находит refresh token по связанному пользователю.
     *
     * @param user пользователь, которому принадлежит токен
     * @return Optional с найденным токеном или пустой Optional
     */
    @Operation(summary = "Найти токен по пользователю",
            description = "Поиск refresh token по пользователю")
    Optional<RefreshToken> findByUser(
            @Parameter(description = "Пользователь") UserEntity user
    );

    /**
     * Удаляет все просроченные токены (где expiryDate меньше текущего времени).
     *
     * @param now текущее время для сравнения
     * @return количество удаленных записей
     */
    @Modifying
    @Query("DELETE FROM RefreshToken rt WHERE rt.expiryDate < :now")
    @Operation(summary = "Удалить просроченные токены",
            description = "Удаляет все refresh token, срок действия которых истек")
    int deleteByExpiryDateBefore(
            @Parameter(description = "Текущее время UTC") @Param("now") Instant now
    );

    /**
     * Удаляет все токены конкретного пользователя.
     *
     * @param user пользователь, чьи токены нужно удалить
     * @return количество удаленных записей
     */
    @Modifying
    @Operation(summary = "Удалить все токены пользователя",
            description = "Удаляет все refresh token конкретного пользователя (используется при logout)")
    int deleteByUser(
            @Parameter(description = "Пользователь") UserEntity user
    );

    /**
     * Проверяет существование токена.
     *
     * @param token значение токена
     * @return true если токен существует, false если нет
     */
    @Operation(summary = "Проверить существование токена",
            description = "Проверяет, существует ли refresh token в базе данных")
    boolean existsByToken(
            @Parameter(description = "Значение токена") String token
    );
}
