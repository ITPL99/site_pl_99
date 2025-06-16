package com.example.site_pl_99.dto;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class CourseDtoResponse {
    private Long id;
    private String titleRu;
    private String titleKg;
    private String descriptionRu;
    private String descriptionKg;
    private Integer price;
    private LocalDateTime dateStarted;
    private LocalDateTime dateEnd;
    private Long imageCourseId;

    public Long getId() {
        return id;
    }

    public CourseDtoResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitleRu() {
        return titleRu;
    }

    public CourseDtoResponse setTitleRu(String titleRu) {
        this.titleRu = titleRu;
        return this;
    }

    public String getTitleKg() {
        return titleKg;
    }

    public CourseDtoResponse setTitleKg(String titleKg) {
        this.titleKg = titleKg;
        return this;
    }

    public String getDescriptionRu() {
        return descriptionRu;
    }

    public CourseDtoResponse setDescriptionRu(String descriptionRu) {
        this.descriptionRu = descriptionRu;
        return this;
    }

    public String getDescriptionKg() {
        return descriptionKg;
    }

    public CourseDtoResponse setDescriptionKg(String descriptionKg) {
        this.descriptionKg = descriptionKg;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public CourseDtoResponse setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public LocalDateTime getDateStarted() {
        return dateStarted;
    }

    public CourseDtoResponse setDateStarted(LocalDateTime dateStarted) {
        this.dateStarted = dateStarted;
        return this;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public CourseDtoResponse setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
        return this;
    }

    public Long getImageCourseId() {
        return imageCourseId;
    }

    public CourseDtoResponse setImageCourseId(Long imageCourseId) {
        this.imageCourseId = imageCourseId;
        return this;
    }
}
