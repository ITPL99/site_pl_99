package com.example.site_pl_99.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MessageDtoRequest {
    private String fullName;
    private String title;
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
