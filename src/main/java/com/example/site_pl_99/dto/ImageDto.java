package com.example.site_pl_99.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO изображения")
public class ImageDto {

    @Schema(description = "Уникальный идентификатор изображения", example = "123")
    private Long id;

    @Schema(description = "Имя файла изображения", example = "photo.jpg")
    private String fileName;
    public String getFileName() {
        return fileName;
    }

    public Long getId() {
        return id;
    }

    public ImageDto setId(Long id) {
        this.id = id;
        return this;
    }

    public ImageDto setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }
}
