package com.example.site_pl_99.dto;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class AvatarDtoResponse {
    private Long id;
    private String fileName;
    private Long workerId;
    private LocalDateTime dateUpload;
    private LocalDateTime dateUpdated;
    private Long userId;

    public Long getId() {
        return id;
    }

    public AvatarDtoResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public AvatarDtoResponse setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public AvatarDtoResponse setWorkerId(Long workerId) {
        this.workerId = workerId;
        return this;
    }

    public LocalDateTime getDateUpload() {
        return dateUpload;
    }

    public AvatarDtoResponse setDateUpload(LocalDateTime dateUpload) {
        this.dateUpload = dateUpload;
        return this;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public AvatarDtoResponse setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public AvatarDtoResponse setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
}
