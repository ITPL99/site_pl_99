package com.example.site_pl_99.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Краткая информация о курсе для предварительного просмотра")
public class CoursePreviewDto {

    @Schema(description = "Уникальный идентификатор курса", example = "101")
    private Long id;

    @Schema(description = "Тип курса (например, online, offline)", example = "online")
    private String CourseType;

    @Schema(description = "Название курса", example = "Курс по веб-разработке")
    private String title;

    @Schema(description = "DTO объекта изображения курса")
    private ImageDto image;

    @Schema(description = "Дата создания курса", example = "2025-06-01", type = "string", format = "date")
    private LocalDate dateCreated;

    @Schema(description = "Дата окончания курса", example = "2025-08-01", type = "string", format = "date")
    private LocalDate dateEnd;

    @Schema(description = "Цена курса", example = "1499.99")
    private Double price;

    @Schema(description = "Статус активности курса", example = "ACTIVE")
    private String active;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCourseType() {
        return CourseType;
    }

    public void setCourseType(String courseType) {
        CourseType = courseType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ImageDto getImage() {
        return image;
    }

    public void setImage(ImageDto image) {
        this.image = image;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
