package com.example.site_pl_99.dto;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class CourseDtoRequest {
    private String title;
    private String description;
    private Long price;
    private LocalDateTime dateStart;
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
