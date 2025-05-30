package com.example.site_pl_99.dto;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class CourseDtoResponse {
    private Long id;
    private String title;
    private String description;
    private Long price;
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
    private Long workerId;
    private Long userId;
    private Long userIdUpdated;

    public Long getId() {
        return id;
    }

    public CourseDtoResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public CourseDtoResponse setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CourseDtoResponse setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getPrice() {
        return price;
    }

    public CourseDtoResponse setPrice(Long price) {
        this.price = price;
        return this;
    }

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public CourseDtoResponse setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
        return this;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public CourseDtoResponse setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
        return this;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public CourseDtoResponse setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public CourseDtoResponse setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
        return this;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public CourseDtoResponse setWorkerId(Long workerId) {
        this.workerId = workerId;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public CourseDtoResponse setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Long getUserIdUpdated() {
        return userIdUpdated;
    }

    public CourseDtoResponse setUserIdUpdated(Long userIdUpdated) {
        this.userIdUpdated = userIdUpdated;
        return this;
    }
}
