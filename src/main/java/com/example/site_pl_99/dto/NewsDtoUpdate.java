package com.example.site_pl_99.dto;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class NewsDtoUpdate {
    private String titleRu;
    private String titleKg;
    private String subTitleRu;
    private String subTitleKg;
    private String descriptionRu;
    private String descriptionKg;
    private ImageDto imageSmall;
    private ImageDto imageFull;
    private List<ImageDto> images;
    private VideoDto video;

    public String getTitleRu() {
        return titleRu;
    }

    public NewsDtoUpdate setTitleRu(String titleRu) {
        this.titleRu = titleRu;
        return this;
    }

    public String getTitleKg() {
        return titleKg;
    }

    public NewsDtoUpdate setTitleKg(String titleKg) {
        this.titleKg = titleKg;
        return this;
    }

    public String getSubTitleRu() {
        return subTitleRu;
    }

    public NewsDtoUpdate setSubTitleRu(String subTitleRu) {
        this.subTitleRu = subTitleRu;
        return this;
    }

    public String getSubTitleKg() {
        return subTitleKg;
    }

    public NewsDtoUpdate setSubTitleKg(String subTitleKg) {
        this.subTitleKg = subTitleKg;
        return this;
    }

    public String getDescriptionRu() {
        return descriptionRu;
    }

    public NewsDtoUpdate setDescriptionRu(String descriptionRu) {
        this.descriptionRu = descriptionRu;
        return this;
    }

    public String getDescriptionKg() {
        return descriptionKg;
    }

    public NewsDtoUpdate setDescriptionKg(String descriptionKg) {
        this.descriptionKg = descriptionKg;
        return this;
    }

    public ImageDto getImageSmall() {
        return imageSmall;
    }

    public NewsDtoUpdate setImageSmall(ImageDto imageSmall) {
        this.imageSmall = imageSmall;
        return this;
    }

    public ImageDto getImageFull() {
        return imageFull;
    }

    public NewsDtoUpdate setImageFull(ImageDto imageFull) {
        this.imageFull = imageFull;
        return this;
    }

    public List<ImageDto> getImages() {
        return images;
    }

    public NewsDtoUpdate setImages(List<ImageDto> images) {
        this.images = images;
        return this;
    }

    public VideoDto getVideo() {
        return video;
    }

    public NewsDtoUpdate setVideo(VideoDto video) {
        this.video = video;
        return this;
    }
}
