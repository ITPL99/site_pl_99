package com.example.site_pl_99.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Schema(description = "DTO для предоставление информаций о квалификациях")
public class QualificationDtoResponse {
    @Schema(description = "Уникальный идентификатор квалификации(ставится автоматически сервером)", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    @Schema(description = "Название квалификации", example = "Backend developer")
    private String title;
    @Schema(description = "Описание квалификации",example = "Backend разработчик, обучение на языках java и python")
    private String description;
    @Schema(description = "ID пользователя, создавшего квалификацию", example = "10")
    private Long userId;
    @Schema(description = "Дата создания квалификации(Ставится автоматически сервером)", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime dateCreated;
    @Schema(description = "Дата обновления квалификации(Ставится автоматически сервером)", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime dateUpdated;
    @Schema(description = "Список идентификаторов работников, связанных с квалификацией")
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
