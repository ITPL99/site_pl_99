package com.example.site_pl_99.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Schema(description = "Объект содержащий информацию о курсе")
public class CourseDtoResponse {

    @Schema(description = "Тип курса",
            example = "SPORT",
            readOnly = true)
    private String courseType;

    @Schema(description = "Название курса на кыргыском языке",
            example = "Java для Начинающих",
            readOnly = true)
    private String title;

    @Schema(description = "Полное описание курса на русском языке",
            example = "Этот курс предназначен для изучения языка программирования Java с нуля. Вы освоите основные концепции через практические задания.",
            readOnly = true)
    private String description;

    @Schema(description = "Стоимость курса",
            example = "25000.00",
            readOnly = true)
    private Double price;

    @Schema(description = "Дата начала курса в формате ГГГГ-ММ-ДД",
            example = "2025-09-01",
            readOnly = true)
    private LocalDate dateStart;

    @Schema(description = "Дата окончания курса в формате ГГГГ-ММ-ДД",
            example = "2025-12-31",
            readOnly = true)
    private LocalDate dateEnd;

    @Schema(description = "Имя файла изображения курса, доступного по URL",
            example = "java_course_image.jpg",
            readOnly = true)
    private String imageFileName;

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
}