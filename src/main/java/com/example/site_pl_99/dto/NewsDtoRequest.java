package com.example.site_pl_99.dto;

import java.util.List;

public class NewsDtoRequest {
    private String title;
    private String subTitle;
    private String description;
    private long imageSmallId;
    private long imageFullId;
    private List<Long> imagesId;
    private long VideoId;

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

    public long getImageSmallId() {
        return imageSmallId;
    }

    public void setImageSmallId(long imageSmallId) {
        this.imageSmallId = imageSmallId;
    }

    public long getImageFullId() {
        return imageFullId;
    }

    public void setImageFullId(long imageFullId) {
        this.imageFullId = imageFullId;
    }

    public List<Long> getImagesId() {
        return imagesId;
    }

    public void setImagesId(List<Long> imagesId) {
        this.imagesId = imagesId;
    }

    public long getVideoId() {
        return VideoId;
    }

    public void setVideoId(long videoId) {
        VideoId = videoId;
    }
}
