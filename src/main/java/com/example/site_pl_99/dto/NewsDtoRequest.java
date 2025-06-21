package com.example.site_pl_99.dto;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class NewsDtoRequest {
    private String titleRu;
    private String titleKg;
    private String subTitleRu;
    private String subTitleKg;
    private String descriptionRu;
    private String descriptionKg;
    private ImageDto imageSmall;
    private ImageDto imageFull;
    private List<ImageDto> imagesFile;
    private VideoDto VideoFileName;

    public String getTitleRu() {
        return titleRu;
    }

    public NewsDtoRequest setTitleRu(String titleRu) {
        this.titleRu = titleRu;
        return this;
    }

    public String getTitleKg() {
        return titleKg;
    }

    public NewsDtoRequest setTitleKg(String titleKg) {
        this.titleKg = titleKg;
        return this;
    }

    public String getSubTitleRu() {
        return subTitleRu;
    }

    public NewsDtoRequest setSubTitleRu(String subTitleRu) {
        this.subTitleRu = subTitleRu;
        return this;
    }

    public String getSubTitleKg() {
        return subTitleKg;
    }

    public NewsDtoRequest setSubTitleKg(String subTitleKg) {
        this.subTitleKg = subTitleKg;
        return this;
    }

    public String getDescriptionRu() {
        return descriptionRu;
    }

    public NewsDtoRequest setDescriptionRu(String descriptionRu) {
        this.descriptionRu = descriptionRu;
        return this;
    }

    public String getDescriptionKg() {
        return descriptionKg;
    }

    public NewsDtoRequest setDescriptionKg(String descriptionKg) {
        this.descriptionKg = descriptionKg;
        return this;
    }

    public ImageDto getImageSmall() {
        return imageSmall;
    }

    public NewsDtoRequest setImageSmall(ImageDto imageSmall) {
        this.imageSmall = imageSmall;
        return this;
    }

    public ImageDto getImageFull() {
        return imageFull;
    }

    public NewsDtoRequest setImageFull(ImageDto imageFull) {
        this.imageFull = imageFull;
        return this;
    }

    public List<ImageDto> getImagesFile() {
        return imagesFile;
    }

    public NewsDtoRequest setImagesFile(List<ImageDto> imagesFile) {
        this.imagesFile = imagesFile;
        return this;
    }

    public VideoDto getVideoFileName() {
        return VideoFileName;
    }

    public NewsDtoRequest setVideoFileName(VideoDto videoFileName) {
        VideoFileName = videoFileName;
        return this;
    }
}
