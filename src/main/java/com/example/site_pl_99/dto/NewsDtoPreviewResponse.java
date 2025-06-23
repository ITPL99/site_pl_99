package com.example.site_pl_99.dto;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class NewsDtoPreviewResponse {
    private Long id;
    private String title;
    private String subTitle;
    private LocalDateTime dateCreated;
    private String imageSmall;

    public Long getId() {
        return id;
    }

    public NewsDtoPreviewResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public NewsDtoPreviewResponse setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public NewsDtoPreviewResponse setSubTitle(String subTitle) {
        this.subTitle = subTitle;
        return this;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public NewsDtoPreviewResponse setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public String getImageSmall() {
        return imageSmall;
    }

    public NewsDtoPreviewResponse setImageSmall(String imageSmall) {
        this.imageSmall = imageSmall;
        return this;
    }
}
