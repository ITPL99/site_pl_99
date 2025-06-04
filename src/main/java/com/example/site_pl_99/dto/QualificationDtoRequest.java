package com.example.site_pl_99.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
@Schema(description = "DTO-запрос с информаций о квалификаций")
@RequiredArgsConstructor
public class QualificationDtoRequest {
    @Schema(description = "Заголовок квалификаций")
    private String title;
    @Schema(description = "Описание квалификаций")
    private String description;

    public String getTitle() {
        return title;
    }

    public QualificationDtoRequest setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public QualificationDtoRequest setDescription(String description) {
        this.description = description;
        return this;
    }
}
