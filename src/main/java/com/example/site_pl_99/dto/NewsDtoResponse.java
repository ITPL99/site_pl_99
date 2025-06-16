package com.example.site_pl_99.dto;

import com.example.site_pl_99.enums.ActiveNews;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class NewsDtoResponse {
    private Long id;
    private String titleRu;
    private String titleKg;
    private String subTitleRu;
    private String subTitleKg;
    private String descriptionRu;
    private String descriptionKg;
    private LocalDateTime dateCreated;
    private ActiveNews activeNews;
    private Long imageSmallId;
    private Long imageFullId;
    private List<Long> images;
    private Long videoId;

    public Long getId() {
        return id;
    }

    public NewsDtoResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitleRu() {
        return titleRu;
    }

    public NewsDtoResponse setTitleRu(String titleRu) {
        this.titleRu = titleRu;
        return this;
    }

    public String getTitleKg() {
        return titleKg;
    }

    public NewsDtoResponse setTitleKg(String titleKg) {
        this.titleKg = titleKg;
        return this;
    }

    public String getSubTitleRu() {
        return subTitleRu;
    }

    public NewsDtoResponse setSubTitleRu(String subTitleRu) {
        this.subTitleRu = subTitleRu;
        return this;
    }

    public String getSubTitleKg() {
        return subTitleKg;
    }

    public NewsDtoResponse setSubTitleKg(String subTitleKg) {
        this.subTitleKg = subTitleKg;
        return this;
    }

    public String getDescriptionRu() {
        return descriptionRu;
    }

    public NewsDtoResponse setDescriptionRu(String descriptionRu) {
        this.descriptionRu = descriptionRu;
        return this;
    }

    public String getDescriptionKg() {
        return descriptionKg;
    }

    public NewsDtoResponse setDescriptionKg(String descriptionKg) {
        this.descriptionKg = descriptionKg;
        return this;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public NewsDtoResponse setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public ActiveNews getActiveNews() {
        return activeNews;
    }

    public NewsDtoResponse setActiveNews(ActiveNews activeNews) {
        this.activeNews = activeNews;
        return this;
    }

    public Long getImageSmallId() {
        return imageSmallId;
    }

    public NewsDtoResponse setImageSmallId(Long imageSmallId) {
        this.imageSmallId = imageSmallId;
        return this;
    }

    public Long getImageFullId() {
        return imageFullId;
    }

    public NewsDtoResponse setImageFullId(Long imageFullId) {
        this.imageFullId = imageFullId;
        return this;
    }

    public List<Long> getImages() {
        return images;
    }

    public NewsDtoResponse setImages(List<Long> images) {
        this.images = images;
        return this;
    }

    public Long getVideoId() {
        return videoId;
    }

    public NewsDtoResponse setVideoId(Long videoId) {
        this.videoId = videoId;
        return this;
    }
}
