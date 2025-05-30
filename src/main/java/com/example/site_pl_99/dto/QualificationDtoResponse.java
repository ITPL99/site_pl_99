package com.example.site_pl_99.dto;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class QualificationDtoResponse {
    private Long id;
    private String title;
    private String description;
    private Long userId;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
    private List<Long> workersIdList;

    public Long getId() {
        return id;
    }

    public QualificationDtoResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public QualificationDtoResponse setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public QualificationDtoResponse setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public QualificationDtoResponse setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public QualificationDtoResponse setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public QualificationDtoResponse setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
        return this;
    }

    public List<Long> getWorkersIdList() {
        return workersIdList;
    }

    public QualificationDtoResponse setWorkersIdList(List<Long> workersIdList) {
        this.workersIdList = workersIdList;
        return this;
    }
}
