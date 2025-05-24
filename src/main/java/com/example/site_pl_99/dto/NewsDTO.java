package com.example.site_pl_99.dto;

import com.example.site_pl_99.entity.News;

import java.time.LocalDateTime;

public class NewsDTO {
    private Long id;
    private String title;
    private String description;
    private Long userId;
    private LocalDateTime dateCreated;

    public NewsDTO() {
    }

    public NewsDTO(News news) {
        this.id = news.getId();
        this.title = news.getTitle();
        this.description = news.getDescription();
        this.userId = news.getUser().getId();
        this.dateCreated = news.getDateCreated();
    }

    public Long getId() {
        return id;
    }

    public NewsDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public NewsDTO setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
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

    public Long getUserId() {
        return userId;
    }

    public NewsDTO setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
}
