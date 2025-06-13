package com.example.site_pl_99.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Schema(description = "DTO-запрос с информацией об сообщении")
@RequiredArgsConstructor
public class MessageDtoResponse {
    @Schema(description = "Айди сообщение" , example = "2")
    private Long id;
    @Schema(description = "Имя того кто написал если пусто то аноним",example = "Эмир Анарбаев")
    private String fullName;
    @Schema(description = "Заголовок сообщение",example = "До меня домогался препод")
    private String title;
    @Schema(description = "Само сообщение",example = "он лапал меня на паре т.д")
    private String message;
    @Schema(description = "Дата создания сообщения",example = "2025-09-01T10:00:00")
    private LocalDateTime dateCreated;
    @Schema(description = "Дата обновления статуса сообщения" , example = "2025-09-01T10:00:00")
    private LocalDateTime dateUpdated;
    @Schema(description = "Почта лицея", example = "plit99@gmail.com")
    private String mailName;
    @Schema(description = "Статус сообщения", example = "по умолчанию CONSIDER ещё может быть COMPLETE, REJECT")
    private String messageStatus;

    public Long getId() {
        return id;
    }

    public MessageDtoResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public MessageDtoResponse setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public MessageDtoResponse setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public MessageDtoResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public MessageDtoResponse setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public MessageDtoResponse setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
        return this;
    }

    public String getMailName() {
        return mailName;
    }

    public MessageDtoResponse setMailName(String mailName) {
        this.mailName = mailName;
        return this;
    }

    public String getMessageStatus() {
        return messageStatus;
    }

    public MessageDtoResponse setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
        return this;
    }
}
