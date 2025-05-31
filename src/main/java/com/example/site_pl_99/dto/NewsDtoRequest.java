package com.example.site_pl_99.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NewsDtoRequest {
    private String title;
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
