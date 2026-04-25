package com.example.site_pl_99.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

/**
 * DTO для ответа с полной информацией о курсе.
 * <p>
 * Содержит все данные о курсе, включая мультиязычные названия и описания.
 * Используется для отображения детальной информации о курсе в интерфейсе.
 * </p>
 *
 * @author PL99 Team
 * @since 1.0
 */
@Schema(description = "Полная информация о курсе с мультиязычной поддержкой")
public class CourseDtoResponse {

    @Schema(
            description = """
                    Уникальный идентификатор курса в базе данных.
                    
                    **Использование:**
                    - Для получения детальной информации о курсе
                    - Для обновления или удаления курса
                    - Для ссылок на курс из других сущностей
                    
                    **Пример:** 1001
                    """,
            example = "1001",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Long id;

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
            allowableValues = {"SPORT", "LANGUAGE", "PROFESSION"}
    )
    private String courseType;

    @Schema(
            description = """
                    Основное название курса.
                    
                    **Примечание:**
                    - Может быть на русском или кыргызском языке
                    - Выбирается в зависимости от локали пользователя
                    - Для мультиязычного отображения используйте отдельные поля
                    
                    **Пример:** Курс Java для начинающих
                    """,
            example = "Курс Java для начинающих",
            maxLength = 255
    )
    private String title;

    @Schema(
            description = """
                    Подробное описание курса.
                    
                    **Примечание:**
                    - Может быть на русском или кыргызском языке
                    - Выбирается в зависимости от локали пользователя
                    - Содержит полную информацию о курсе
                    
                    **Пример:** Подробный курс по основам языка Java.
                    """,
            example = "Подробный курс по основам языка Java.",
            maxLength = 5000
    )
    private String description;

    @Schema(
            description = """
                    Стоимость курса в национальной валюте.
                    
                    **Формат:**
                    - Десятичное число с двумя знаками после запятой
                    - Включает все налоги и сборы
                    
                    **Пример:** 2999.99
                    """,
            example = "2999.99",
            minimum = "0",
            maximum = "999999.99"
    )
    private Double price;

    @Schema(
            description = """
                    Дата начала проведения курса.
                    
                    **Формат:** ISO 8601 (YYYY-MM-DD)
                    **Часовой пояс:** UTC
                    
                    **Пример:** 2025-07-01
                    """,
            example = "2025-07-01",
            type = "string",
            format = "date"
    )
    private LocalDate dateStart;

    @Schema(
            description = """
                    Дата окончания проведения курса.
                    
                    **Формат:** ISO 8601 (YYYY-MM-DD)
                    **Часовой пояс:** UTC
                    
                    **Пример:** 2025-08-31
                    """,
            example = "2025-08-31",
            type = "string",
            format = "date"
    )
    private LocalDate dateEnd;

    @Schema(
            description = """
                    Имя файла изображения курса.
                    
                    **Использование:**
                    - Для формирования URL изображения
                    - Для отображения в интерфейсе
                    
                    **Формат:**
                    - Расширение: .jpg, .png, .gif, .webp
                    - Хранится в MinIO
                    
                    **Пример:** java-course.png
                    """,
            example = "java-course.png",
            maxLength = 255
    )
    private String imageFileName;

    @Schema(
            description = """
                    Статус активности курса.
                    
                    **Доступные значения:**
                    - ACTIVE - Курс активен и отображается
                    - INACTIVE - Курс существует, но не отображается
                    - DELETED - Курс удален
                    - NEW - Новый курс, требует проверки
                    
                    **Пример:** ACTIVE
                    """,
            example = "ACTIVE",
            allowableValues = {"ACTIVE", "INACTIVE", "DELETED", "NEW"}
    )
    private String active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
