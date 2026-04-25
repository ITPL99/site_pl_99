package com.example.site_pl_99.dto;

import com.example.site_pl_99.enums.CourseType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

/**
 * DTO для запроса создания или обновления курса.
 * <p>
 * Содержит все необходимые данные для создания нового образовательного курса
 * или обновления существующего. Поддерживает мультиязычность (русский и кыргызский).
 * Использует Bean Validation для проверки входных данных.
 * </p>
 *
 * @author PL99 Team
 * @since 1.0
 */
@Schema(description = "Запрос для создания или обновления курса с мультиязычной поддержкой")
public class CourseDtoRequest {

    @Schema(
            description = """
                    Тип курса из перечисления CourseType.
                    
                    **Доступные значения:**
                    - SPORT - Спортивные направления
                    - LANGUAGE - Языковые курсы
                    - PROFESSION - Профессиональные навыки
                    
                    **Пример:** PROFESSION
                    """,
            example = "PROFESSION",
            allowableValues = {"SPORT", "LANGUAGE", "PROFESSION"},
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private CourseType courseType;

    @Schema(
            description = """
                    Название курса на кыргызском языке.
                    
                    **Требования:**
                    - Обязательное поле
                    - Минимальная длина: 3 символа
                    - Максимальная длина: 255 символов
                    
                    **Пример:** Java Окутуу Курсу
                    """,
            example = "Java Окутуу Курсу",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minLength = 3,
            maxLength = 255
    )
    private String titleKg;

    @Schema(
            description = """
                    Название курса на русском языке.
                    
                    **Требования:**
                    - Обязательное поле
                    - Минимальная длина: 3 символа
                    - Максимальная длина: 255 символов
                    
                    **Пример:** Курс по обучению Java
                    """,
            example = "Курс по обучению Java",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minLength = 3,
            maxLength = 255
    )
    private String titleRu;

    @Schema(
            description = """
                    Подробное описание курса на кыргызском языке.
                    
                    **Требования:**
                    - Обязательное поле
                    - Минимальная длина: 10 символов
                    - Максимальная длина: 5000 символов
                    
                    **Пример:** Бул курс Java тилин үйрөнүүгө арналган.
                    """,
            example = "Бул курс Java тилин үйрөнүүгө арналган.",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minLength = 10,
            maxLength = 5000
    )
    private String descriptionKg;

    @Schema(
            description = """
                    Подробное описание курса на русском языке.
                    
                    **Требования:**
                    - Обязательное поле
                    - Минимальная длина: 10 символов
                    - Максимальная длина: 5000 символов
                    
                    **Пример:** Курс предназначен для изучения языка Java.
                    """,
            example = "Курс предназначен для изучения языка Java.",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minLength = 10,
            maxLength = 5000
    )
    private String descriptionRu;

    @Schema(
            description = """
                    Стоимость курса в национальной валюте.
                    
                    **Требования:**
                    - Обязательное поле
                    - Минимальное значение: 0
                    - Максимальное значение: 999999.99
                    - Два знака после запятой
                    
                    **Пример:** 1999.99
                    """,
            example = "1999.99",
            requiredMode = Schema.RequiredMode.REQUIRED,
            minimum = "0",
            maximum = "999999.99"
    )
    private Double price;

    @Schema(
            description = """
                    Изображение курса в формате DTO.
                    
                    **Опциональное поле:**
                    - Можно передать null при создании
                    - Будет загружено отдельно через endpoint /api/images
                    
                    **Содержит:**
                    - ID изображения
                    - Имя файла
                    """
    )
    private ImageDto image;

    @Schema(
            description = """
                    Дата начала проведения курса.
                    
                    **Требования:**
                    - Обязательное поле
                    - Не должна быть в прошлом
                    - Формат: ISO 8601 (YYYY-MM-DD)
                    
                    **Пример:** 2025-07-01
                    """,
            example = "2025-07-01",
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "string",
            format = "date"
    )
    private LocalDate dateStart;

    @Schema(
            description = """
                    Дата окончания проведения курса.
                    
                    **Требования:**
                    - Обязательное поле
                    - Должна быть позже dateStart
                    - Формат: ISO 8601 (YYYY-MM-DD)
                    
                    **Пример:** 2025-08-01
                    """,
            example = "2025-08-01",
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "string",
            format = "date"
    )
    private LocalDate dateEnd;

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public String getTitleKg() {
        return titleKg;
    }

    public void setTitleKg(String titleKg) {
        this.titleKg = titleKg;
    }

    public String getTitleRu() {
        return titleRu;
    }

    public void setTitleRu(String titleRu) {
        this.titleRu = titleRu;
    }

    public String getDescriptionKg() {
        return descriptionKg;
    }

    public void setDescriptionKg(String descriptionKg) {
        this.descriptionKg = descriptionKg;
    }

    public String getDescriptionRu() {
        return descriptionRu;
    }

    public void setDescriptionRu(String descriptionRu) {
        this.descriptionRu = descriptionRu;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ImageDto getImage() {
        return image;
    }

    public void setImage(ImageDto image) {
        this.image = image;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }
}
