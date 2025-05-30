package com.example.site_pl_99.dto;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class NewsDtoResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
    private Long authorId;
    private Long userIdUpdated;
    private List<Long> imagesId;
    private List<Long> videosId;

    public Long getId() {
        return id;
    }

    public NewsDtoResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public NewsDtoResponse setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public NewsDtoResponse setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public NewsDtoResponse setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public NewsDtoResponse setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
        return this;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public NewsDtoResponse setAuthorId(Long authorId) {
        this.authorId = authorId;
        return this;
    }

    public Long getUserIdUpdated() {
        return userIdUpdated;
    }

    public NewsDtoResponse setUserIdUpdated(Long userIdUpdated) {
        this.userIdUpdated = userIdUpdated;
        return this;
    }

    public List<Long> getImagesId() {
        return imagesId;
    }

    public NewsDtoResponse setImagesId(List<Long> imagesId) {
        this.imagesId = imagesId;
        return this;
    }

    public List<Long> getVideosId() {
        return videosId;
    }

    public NewsDtoResponse setVideosId(List<Long> videosId) {
        this.videosId = videosId;
        return this;
    }
}
