package com.example.site_pl_99.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Schema(description = "DTO для предоставления информаций работнике")
@RequiredArgsConstructor
public class WorkerDtoResponse {
    @Schema(description = "Уникальный идентификатор рабочего(ставится автоматически сервером)", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    @Schema(description = "Полное имя работника")
    private String fullName;
    @Schema(description = "Дата рождения работника")
    private LocalDate birthDate;
    @Schema(description = "Биография работника")
    private String biography;
    @Schema(description = "Профессия работника(охранник, мастер, учитель, уборщик")
    private String profession;
    @Schema(description = "Дата создания профиля работника(Ставится автоматически сервером)", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime dateCreated;
    @Schema(description = "Дата обновления информации о работника(Ставится автоматически сервером)", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime dateUpdated;
    @Schema(description = "Иденитификатор пользоавателяб который добавил рабочего",example = "2")
    private Long userId;
    @Schema(description = "Идентификатор аватара рабочего")
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
