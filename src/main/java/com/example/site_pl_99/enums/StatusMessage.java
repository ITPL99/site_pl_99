package com.example.site_pl_99.enums;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Статусы обработки сообщений из контактной формы.
 * <p>
 * Определяет этап обработки сообщений, полученных через контактную форму.
 * Используется для отслеживания состояния сообщений в административной панели.
 * </p>
 *
 * @author PL99 Team
 * @since 1.0
 */
@Schema(description = "Статусы обработки сообщений")
public enum StatusMessage {
    
    @Schema(description = "Рассматривается - новое сообщение, получено и ожидает обработки")
    CONSIDER,
    
    @Schema(description = "Отклонено - сообщение отклонено как спам или неактуальное")
    REJECTED,
    
    @Schema(description = "Принято - сообщение принято в работу и обрабатывается")
    ACCEPTED;
}
