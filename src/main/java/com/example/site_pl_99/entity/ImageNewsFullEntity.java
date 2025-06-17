package com.example.site_pl_99.entity;

import com.example.site_pl_99.enums.ActiveJob;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "images_news_full")
@RequiredArgsConstructor
public class ImageNewsFullEntity extends BaseEntity {
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

    public ImageNewsFullEntity setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public NewsEntity getNewsEntity() {
        return newsEntity;
    }

    public ImageNewsFullEntity setNewsEntity(NewsEntity newsEntity) {
        this.newsEntity = newsEntity;
        return this;
    }
}
