package com.example.site_pl_99.entity;

import com.example.site_pl_99.enums.ActiveJob;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "images_news_small")
@RequiredArgsConstructor
public class ImageNewsSmallEntity extends BaseEntity {
    @Column(name = "file_name", nullable = false, unique = true)
    private String fileName;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "news_id", referencedColumnName = "id", unique = true)
    private NewsEntity newsEntity;
    @Column(name = "active")
    private ActiveJob active;

    public ActiveJob getActive() {
        return active;
    }

    public void setActive(ActiveJob active) {
        this.active = active;
    }

    public String getFileName() {
        return fileName;
    }

    public ImageNewsSmallEntity setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public NewsEntity getNewsEntity() {
        return newsEntity;
    }

    public ImageNewsSmallEntity setNewsEntity(NewsEntity newsEntity) {
        this.newsEntity = newsEntity;
        return this;
    }
}
