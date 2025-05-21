package com.example.site_pl_99.dto;

import java.time.LocalDateTime;

public class NewsDTO {
    private String title;
    private String description;
    private Long userId;

    public NewsDTO() {
    }

    public NewsDTO(String title,
                   String description,
                   LocalDateTime dateCreated,
                   Long userId) {
        this.title = title;
        this.description = description;
        this.dateCreated = dateCreated;
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public NewsDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public NewsDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public NewsDTO setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public NewsDTO setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
}
