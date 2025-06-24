package com.example.site_pl_99.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "DTO ответа с полной информацией о новости")
@RequiredArgsConstructor
public class NewsDtoResponse {

    @Schema(description = "Уникальный идентификатор новости", example = "101")
    private Long id;

    @Schema(description = "Заголовок новости", example = "Запуск нового курса")
    private String title;

    @Schema(description = "Подзаголовок новости", example = "Курс стартует в июле")
    private String subTitle;

    @Schema(description = "Полное описание новости", example = "Подробное описание новостного события...")
    private String description;

    @Schema(description = "Дата создания новости", example = "2025-06-25T10:15:30")
    private LocalDateTime dateCreate;

    @Schema(description = "Имя файла миниатюрного изображения", example = "news_small_101.jpg")
    private String imageSmallFileName;

    @Schema(description = "Имя файла полноразмерного изображения", example = "news_full_101.jpg")
    private String imageFullFileName;

    @Schema(description = "Список имен файлов дополнительных изображений")
    private List<String> imagesFilesName;

    @Schema(description = "Имя файла видео, связанного с новостью", example = "video_news_101.mp4")
    private String videoFile;

    @Schema(description = "Статус активности новости", example = "ACTIVE")
    private String active;


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

    public String getSubTitle() {
        return subTitle;
    }

    public NewsDtoResponse setSubTitle(String subTitle) {
        this.subTitle = subTitle;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public NewsDtoResponse setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    public NewsDtoResponse setDateCreate(LocalDateTime dateCreate) {
        this.dateCreate = dateCreate;
        return this;
    }

    public String getImageSmallFileName() {
        return imageSmallFileName;
    }

    public NewsDtoResponse setImageSmallFileName(String imageSmallFileName) {
        this.imageSmallFileName = imageSmallFileName;
        return this;
    }

    public String getImageFullFileName() {
        return imageFullFileName;
    }

    public NewsDtoResponse setImageFullFileName(String imageFullFileName) {
        this.imageFullFileName = imageFullFileName;
        return this;
    }

    public List<String> getImagesFilesName() {
        return imagesFilesName;
    }

    public NewsDtoResponse setImagesFilesName(List<String> imagesFilesName) {
        this.imagesFilesName = imagesFilesName;
        return this;
    }

    public String getVideoFile() {
        return videoFile;
    }

    public NewsDtoResponse setVideoFile(String videoFile) {
        this.videoFile = videoFile;
        return this;
    }

    public String getActive() {
        return active;
    }

    public NewsDtoResponse setActive(String active) {
        this.active = active;
        return this;
    }
}
