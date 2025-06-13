package com.example.site_pl_99.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Schema(description = "DTO-ответ с информацией об аватаре работника")
public class AvatarDtoResponse {
    @Schema(description = "Уникальный идентификатор аватара(генерируется автоматически)",accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    @Schema(description = "Имя файла аватара", example = "avatar.png")
    private String fileName;
    @Schema(description = "ID работника, которому принадлежит аватар", example = "45")
    private Long workerId;
    @Schema(description = "Дата загрузки аватара(устанавливается автоматичкски сервером)",accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime dateUpload;
    @Schema (description = "Дата последнего обновления (устанавливается автоматически сервером)", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime dateUpdated;
    @Schema(description = "D пользователя, который загрузил аватар", example = "12")
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
