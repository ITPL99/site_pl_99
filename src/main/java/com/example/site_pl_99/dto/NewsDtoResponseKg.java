package com.example.site_pl_99.dto;

import com.example.site_pl_99.enums.Active;

import java.time.LocalDateTime;
import java.util.List;

public class NewsDtoResponseKg {
    private String title;
    private String subTitle;
    private String description;
    private LocalDateTime dateCreate;
    private long imageSmallId;
    private long imagefullId;
    private List<Long> imagesId;
    private long videoId;
    private Active active;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDateTime dateCreate) {
        this.dateCreate = dateCreate;
    }

    public long getImageSmallId() {
        return imageSmallId;
    }

    public void setImageSmallId(long imageSmallId) {
        this.imageSmallId = imageSmallId;
    }

    public long getImagefullId() {
        return imagefullId;
    }

    public void setImagefullId(long imagefullId) {
        this.imagefullId = imagefullId;
    }

    public List<Long> getImagesId() {
        return imagesId;
    }

    public void setImagesId(List<Long> imagesId) {
        this.imagesId = imagesId;
    }

    public long getVideoId() {
        return videoId;
    }

    public void setVideoId(long videoId) {
        this.videoId = videoId;
    }

    public Active getActive() {
        return active;
    }

    public void setActive(Active active) {
        this.active = active;
    }
}
