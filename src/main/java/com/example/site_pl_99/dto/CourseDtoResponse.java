package com.example.site_pl_99.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Ответ с полной информацией о курсе")
public class CourseDtoResponse {

    @Schema(description = "Идентификатор курса", example = "1001")
    private Long id;

    @Schema(description = "Тип курса (например, online/offline)", example = "online")
    private String courseType;

    @Schema(description = "Заголовок курса (объединённое название на одном языке)", example = "Курс Java для начинающих")
    private String title;

    @Schema(description = "Описание курса", example = "Подробный курс по основам языка Java.")
    private String description;

    @Schema(description = "Цена курса", example = "2999.99")
    private Double price;

    @Schema(description = "Дата начала курса", example = "2025-07-01", type = "string", format = "date")
    private LocalDate dateStart;

    @Schema(description = "Дата окончания курса", example = "2025-08-31", type = "string", format = "date")
    private LocalDate dateEnd;

    @Schema(description = "Имя файла изображения курса", example = "java-course.png")
    private String imageFileName;

    @Schema(description = "Статус активности курса", example = "ACTIVE")
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
