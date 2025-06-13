package com.example.site_pl_99.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Schema(description = "DTO для запроса с информацией об аватаре пользователя")
public class AvatarDtoRequest {
    @Schema(description = "Имя файла аватара", example = "avatar.png", required = true)
    private String fileName;
    public String getFileName() {
        return fileName;
    }

    public AvatarDtoRequest setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }
}
