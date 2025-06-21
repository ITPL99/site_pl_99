package com.example.site_pl_99.entity;

import com.example.site_pl_99.enums.Active;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "news")
public class NewsEntity extends BaseEntity {

    private String titleRu;
    private String titleKg;
    private String subTitleRu;
    private String subTitleKg;
    private String descriptionRu;
    private String descriptionKg;
    private LocalDateTime dateCreate;
    @OneToOne
    @JoinColumn(name = "image_small")
    private ImageEntity imageSmall;
    @OneToOne
    @JoinColumn(name = "image_full")
    private ImageEntity imageFull;

    @OneToMany
    @JoinColumn(name = "image_content")
    private List<ImageEntity> images;

    @OneToOne
   @JoinColumn(name = "video_content")
    private VideoEntity video;

    @Enumerated(EnumType.STRING)
    private Active active;

    public String getTitleRu() {
        return titleRu;
    }

    public NewsEntity setTitleRu(String titleRu) {
        this.titleRu = titleRu;
        return this;
    }

    public String getTitleKg() {
        return titleKg;
    }

    public NewsEntity setTitleKg(String titleKg) {
        this.titleKg = titleKg;
        return this;
    }

    public String getSubTitleRu() {
        return subTitleRu;
    }

    public NewsEntity setSubTitleRu(String subTitleRu) {
        this.subTitleRu = subTitleRu;
        return this;
    }

    public String getSubTitleKg() {
        return subTitleKg;
    }

    public NewsEntity setSubTitleKg(String subTitleKg) {
        this.subTitleKg = subTitleKg;
        return this;
    }

    public String getDescriptionRu() {
        return descriptionRu;
    }

    public NewsEntity setDescriptionRu(String descriptionRu) {
        this.descriptionRu = descriptionRu;
        return this;
    }

    public String getDescriptionKg() {
        return descriptionKg;
    }

    public NewsEntity setDescriptionKg(String descriptionKg) {
        this.descriptionKg = descriptionKg;
        return this;
    }

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    public NewsEntity setDateCreate(LocalDateTime dateCreate) {
        this.dateCreate = dateCreate;
        return this;
    }

    public ImageEntity getImageSmall() {
        return imageSmall;
    }

    public NewsEntity setImageSmall(ImageEntity imageSmall) {
        this.imageSmall = imageSmall;
        return this;
    }

    public ImageEntity getImageFull() {
        return imageFull;
    }

    public NewsEntity setImageFull(ImageEntity imageFull) {
        this.imageFull = imageFull;
        return this;
    }

    public List<ImageEntity> getImages() {
        return images;
    }

    public NewsEntity setImages(List<ImageEntity> images) {
        this.images = images;
        return this;
    }

    public VideoEntity getVideo() {
        return video;
    }

    public NewsEntity setVideo(VideoEntity video) {
        this.video = video;
        return this;
    }

    public Active getActive() {
        return active;
    }

    public NewsEntity setActive(Active active) {
        this.active = active;
        return this;
    }
}
