package com.example.site_pl_99.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
@Schema(description = "DTO-ответ с информацией о курсах")
@RequiredArgsConstructor
public class CourseDtoResponse {
    @Schema(description = "Уникальный идентификатор курсов (Устанавливается автоматически сервером)",accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    @Schema(description = "Заголовок курса",example = "Китайский язык")
    private String title;
    @Schema(description = "Описание курса")
    private String description;
    @Schema(description = "Цена курса в сомах",example = "1500")
    private Long price;
    @Schema(description = "Дата начала курса", example = "2025-09-01T10:00:00")
    private LocalDateTime dateStart;
    @Schema(description = "Дата окончания курса", example = "2025-12-01T18:00:00")
    private LocalDateTime dateEnd;
    @Schema(description = "Дата создания курса(ставится автоматически сервером)",accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime dateCreated;
    @Schema(description = "Дата обновления курса(ставится автоматически сервером)",accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime dateUpdated;
    @Schema(description = "ID преподавателя, ведущего курс", example = "7")
    private Long workerId;
    @Schema(description = "ID пользователя, создавшего курс", example = "3")
    private Long userId;
    @Schema(description = "ID пользователя, обновившего курс", example = "5")
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
