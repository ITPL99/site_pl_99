package com.example.site_pl_99.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;

@Schema(description = "Данные видеофайла")
@RequiredArgsConstructor
public class VideoDto {

    @Schema(description = "Уникальный идентификатор видео", example = "1")
    private Long id;

    @Schema(description = "Имя видеофайла", example = "video123.mp4")
    private String fileName;

    public Long getId() {
        return id;
    }

    public VideoDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public VideoDto setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }
}
