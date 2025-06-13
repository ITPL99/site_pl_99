package com.example.site_pl_99.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;

@Schema(description = "DTO-запрос с информацией об сообщении")
@RequiredArgsConstructor
public class MessageDtoRequest {
    @Schema(description = "Имя того кто написал если пусто то аноним",example = "Эмир Анарбаев")
    private String fullName;
    @Schema(description = "Заголовок сообщение",example = "До меня домогался препод")
    private String title;
    @Schema(description = "Само сообщение",example = "он лапал меня на паре т.д")
    private String message;

    public String getFullName() {
        return fullName;
    }

    public MessageDtoRequest setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public MessageDtoRequest setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public MessageDtoRequest setMessage(String message) {
        this.message = message;
        return this;
    }
}
