package com.example.site_pl_99.enums;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Статусы активности сущностей в системе.
 * <p>
 * Определяет текущее состояние записей в базе данных.
 * Используется для фильтрации и отображения данных в зависимости от статуса.
 * </p>
 *
 * @author PL99 Team
 * @since 1.0
 */
@Schema(description = "Статусы активности сущностей")
public enum Active {
    
    @Schema(description = "Активная запись - отображается на сайте и доступна для использования")
    ACTIVE,
    
    @Schema(description = "Неактивная запись - существует в системе, но не отображается публично")
    INACTIVE,
    
    @Schema(description = "Удаленная запись - помечена как удаленная (soft delete)")
    DELETED,
    
    @Schema(description = "Заблокированная запись - доступ запрещен")
    BANED,
    
    @Schema(description = "Запись в процессе обновления - временный статус")
    UPDATED,
    
    @Schema(description = "Новая запись - требует проверки или модерации")
    NEW;
}
