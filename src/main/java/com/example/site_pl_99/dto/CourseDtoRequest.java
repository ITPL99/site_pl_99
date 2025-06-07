package com.example.site_pl_99.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
@Schema(description = "DTO-запрос с информацией об курсах")
@RequiredArgsConstructor
public class CourseDtoRequest {
    @Schema(description = "Заголовок курса",example = "Английский язык")
    private String title;
    @Schema(description = "Описание курсов",example = "Обучение до B1 уровня")
    private String description;
    @Schema(description = "Цена курса",example = "2000")
    private Long price;
    @Schema(description = "Дата начала курса", example = "2025-09-01T10:00:00")
    private LocalDateTime dateStart;
    @Schema(description = "Дата окончания курса", example = "2025-12-01T18:00:00")
    private LocalDateTime dateEnd;

    public String getTitle() {
        return title;
    }

    public CourseDtoRequest setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CourseDtoRequest setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getPrice() {
        return price;
    }

    public CourseDtoRequest setPrice(Long price) {
        this.price = price;
        return this;
    }

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public CourseDtoRequest setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
        return this;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public CourseDtoRequest setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
        return this;
    }
}
