package com.example.site_pl_99.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
@Schema(description = "DTO-запрос с информацией о новостях")
@RequiredArgsConstructor
public class NewsDtoRequest {
    @Schema(description = "Заголовок новости", example = "Важное обновление на сайте")
    private String title;
    @Schema(description = "Описание новостей", example = "Сегодня мы запустили новую версию сайта")
    private String description;

    public String getTitle() {
        return title;
    }

    public NewsDtoRequest setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public NewsDtoRequest setDescription(String description) {
        this.description = description;
        return this;
    }
}
