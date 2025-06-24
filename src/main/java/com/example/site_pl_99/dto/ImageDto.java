package com.example.site_pl_99.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Объект, представляющий изображение")
public class ImageDto {

    @Schema(description = "Уникальный идентификатор изображения",
            example = "101")
    private Long Id;

    @Schema(description = "Имя файла изображения",
            example = "course_image_01.jpg")
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public Long getId() {
        return Id;
    }

    public ImageDto setId(Long id) {
        Id = id;
        return this;
    }

    public ImageDto setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }
}
