package com.example.site_pl_99.dto;

import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Schema(description = "Превью новостного материала")
public class NewsDtoPreviewResponse {

    @Schema(description = "Уникальный идентификатор новости", example = "123")
    private Long id;

    @Schema(description = "Заголовок новости", example = "Новый курс стартует в июне")
    private String title;

    @Schema(description = "Подзаголовок новости", example = "Регистрация уже открыта")
    private String subTitle;

    @Schema(description = "Дата создания новости", example = "2023-05-25T15:30:00")
    private LocalDateTime dateCreated;

    @Schema(description = "Название файла с маленьким изображением", example = "news_small_123.jpg")
    private String imageSmall;

    public Long getId() {
        return id;
    }

    public NewsDtoPreviewResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public NewsDtoPreviewResponse setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public NewsDtoPreviewResponse setSubTitle(String subTitle) {
        this.subTitle = subTitle;
        return this;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public NewsDtoPreviewResponse setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public String getImageSmall() {
        return imageSmall;
    }

    public NewsDtoPreviewResponse setImageSmall(String imageSmall) {
        this.imageSmall = imageSmall;
        return this;
    }
}
