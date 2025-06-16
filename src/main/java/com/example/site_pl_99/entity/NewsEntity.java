package com.example.site_pl_99.entity;

import com.example.site_pl_99.enums.ActiveNews;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "news")
@RequiredArgsConstructor
public class NewsEntity extends BaseEntity {
    @Column(name = "title_ru", nullable = false, unique = true)
    private String titleRu;
    @Column(name = "title_kg", nullable = false, unique = true)
    private String titleKg;
    @Column(name = "subtitle_ru", nullable = false, unique = true)
    private String subTitleRu;
    @Column(name = "subtitle_kg", nullable = false, unique = true)
    private String subTitleKg;
    @Column(name = "description_ru", nullable = false)
    private String descriptionRu;
    @Column(name = "description_kg", nullable = false)
    private String descriptionKg;
    @Column(name = "date_created")
    private LocalDateTime dateCreated;
    @Column(name = "active")
    private ActiveNews active;
    @OneToOne(mappedBy = "newsEntity", fetch = FetchType.EAGER)
    private ImageNewsSmallEntity imageNewsSmallEntity;
    @OneToOne(mappedBy = "newsEntity", fetch = FetchType.EAGER)
    private ImageNewsFullEntity imageNewsFullEntity;
    @OneToMany(mappedBy = "newsEntity", fetch = FetchType.EAGER)
    private List<ImageNewsEntity> imageNewsEntityList;
    @OneToOne(mappedBy = "newsEntity",fetch = FetchType.EAGER)
    private VideoNewsEntity videoNewsEntity;

    @PrePersist
    public void prePersist() {
        active = ActiveNews.NEW;
        dateCreated = LocalDateTime.now();
    }

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

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public NewsEntity setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public ActiveNews getActive() {
        return active;
    }

    public NewsEntity setActive(ActiveNews active) {
        this.active = active;
        return this;
    }

    public ImageNewsSmallEntity getImageNewsSmallEntity() {
        return imageNewsSmallEntity;
    }

    public NewsEntity setImageNewsSmallEntity(ImageNewsSmallEntity imageNewsSmallEntity) {
        this.imageNewsSmallEntity = imageNewsSmallEntity;
        return this;
    }

    public ImageNewsFullEntity getImageNewsFullEntity() {
        return imageNewsFullEntity;
    }

    public NewsEntity setImageNewsFullEntity(ImageNewsFullEntity imageNewsFullEntity) {
        this.imageNewsFullEntity = imageNewsFullEntity;
        return this;
    }

    public List<ImageNewsEntity> getImageNewsEntityList() {
        return imageNewsEntityList;
    }

    public NewsEntity setImageNewsEntityList(List<ImageNewsEntity> imageNewsEntityList) {
        this.imageNewsEntityList = imageNewsEntityList;
        return this;
    }

    public VideoNewsEntity getVideoNewsEntity() {
        return videoNewsEntity;
    }

    public NewsEntity setVideoNewsEntity(VideoNewsEntity videoNewsEntity) {
        this.videoNewsEntity = videoNewsEntity;
        return this;
    }
}
