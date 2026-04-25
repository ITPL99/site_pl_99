package com.example.site_pl_99.enums;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Типы образовательных курсов.
 * <p>
 * Классифицирует курсы по направлениям обучения.
 * Используется для фильтрации и категоризации курсов в системе.
 * </p>
 *
 * @author PL99 Team
 * @since 1.0
 */
@Schema(description = "Типы образовательных курсов")
public enum CourseType {
    
    @Schema(description = "Спортивные направления и фитнес курсы")
    SPORT,
    
    @Schema(description = "Языковые курсы - изучение иностранных языков")
    LANGUAGE,
    
    @Schema(description = "Профессиональные навыки и рабочие специальности")
    PROFESSION;
}
