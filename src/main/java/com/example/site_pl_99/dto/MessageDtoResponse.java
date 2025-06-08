package com.example.site_pl_99.dto;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class MessageDtoResponse {
    private Long id;
    private String fullName;
    private String title;
    private String message;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
    private String mailName;

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
}
