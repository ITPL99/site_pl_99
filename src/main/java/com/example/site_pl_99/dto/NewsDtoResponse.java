package com.example.site_pl_99.dto;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class NewsDtoResponse {
    private Long id;
    private String title;
    private String subTitle;
    private String description;
    private LocalDateTime dateCreate;
    private String imageSmallFileName;
    private String imageFullFileName;
    private List<String> imagesFilesName;
    private String videoFile;
    private String active;

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

    public String getSubTitle() {
        return subTitle;
    }

    public NewsDtoResponse setSubTitle(String subTitle) {
        this.subTitle = subTitle;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public NewsDtoResponse setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    public NewsDtoResponse setDateCreate(LocalDateTime dateCreate) {
        this.dateCreate = dateCreate;
        return this;
    }

    public String getImageSmallFileName() {
        return imageSmallFileName;
    }

    public NewsDtoResponse setImageSmallFileName(String imageSmallFileName) {
        this.imageSmallFileName = imageSmallFileName;
        return this;
    }

    public String getImageFullFileName() {
        return imageFullFileName;
    }

    public NewsDtoResponse setImageFullFileName(String imageFullFileName) {
        this.imageFullFileName = imageFullFileName;
        return this;
    }

    public List<String> getImagesFilesName() {
        return imagesFilesName;
    }

    public NewsDtoResponse setImagesFilesName(List<String> imagesFilesName) {
        this.imagesFilesName = imagesFilesName;
        return this;
    }

    public String getVideoFile() {
        return videoFile;
    }

    public NewsDtoResponse setVideoFile(String videoFile) {
        this.videoFile = videoFile;
        return this;
    }

    public String getActive() {
        return active;
    }

    public NewsDtoResponse setActive(String active) {
        this.active = active;
        return this;
    }
}
