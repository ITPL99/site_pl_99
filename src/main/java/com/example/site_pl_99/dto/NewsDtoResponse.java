package com.example.site_pl_99.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Schema(description = "DTO-ответ информацией о новости")
@RequiredArgsConstructor
public class NewsDtoResponse {
    @Schema(description = "Уникальный идентификатор новости (Устанавливается автоматически сервером)",accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    @Schema(description = "Заголовок новости")
    private String title;
    @Schema(description = "Описание новости")
    private String description;
    @Schema(description = "Дата создания новости(ставится автоматически сервером)",accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime dateCreated;
    @Schema(description = "Дата последнего обновления новости(ставится автоматически сервером)",accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime dateUpdated;
    @Schema(description = "Id автора новости",example = "1")
    private Long authorId;
    @Schema(description = "ID пользователя который обновил новость", example = "2")
    private Long userIdUpdated;
    @Schema(description = "Список идентификаторов фотографий, прикрепленных к новости ")
    private List<Long> imagesId;
    @Schema(description = "Список идентификаторов видеозаписей, прикреплённых к новости")
    private List<Long> videosId;

    public Long getId() {
        return id;
    }

    public NewsDtoResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public NewsDtoResponse setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public NewsDtoResponse setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public NewsDtoResponse setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public NewsDtoResponse setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
        return this;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public NewsDtoResponse setAuthorId(Long authorId) {
        this.authorId = authorId;
        return this;
    }

    public Long getUserIdUpdated() {
        return userIdUpdated;
    }

    public NewsDtoResponse setUserIdUpdated(Long userIdUpdated) {
        this.userIdUpdated = userIdUpdated;
        return this;
    }

    public List<Long> getImagesId() {
        return imagesId;
    }

    public NewsDtoResponse setImagesId(List<Long> imagesId) {
        this.imagesId = imagesId;
        return this;
    }

    public List<Long> getVideosId() {
        return videosId;
    }

    public NewsDtoResponse setVideosId(List<Long> videosId) {
        this.videosId = videosId;
        return this;
    }
}
