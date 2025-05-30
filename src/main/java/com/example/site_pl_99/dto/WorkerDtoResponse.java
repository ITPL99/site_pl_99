package com.example.site_pl_99.dto;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public class WorkerDtoResponse {
    private Long id;
    private String fullName;
    private LocalDate birthDate;
    private String biography;
    private String profession;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
    private Long userId;
    private Long avatarId;

    public Long getId() {
        return id;
    }

    public WorkerDtoResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public WorkerDtoResponse setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public WorkerDtoResponse setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public String getBiography() {
        return biography;
    }

    public WorkerDtoResponse setBiography(String biography) {
        this.biography = biography;
        return this;
    }

    public String getProfession() {
        return profession;
    }

    public WorkerDtoResponse setProfession(String profession) {
        this.profession = profession;
        return this;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public WorkerDtoResponse setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public WorkerDtoResponse setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public WorkerDtoResponse setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Long getAvatarId() {
        return avatarId;
    }

    public WorkerDtoResponse setAvatarId(Long avatarId) {
        this.avatarId = avatarId;
        return this;
    }
}
