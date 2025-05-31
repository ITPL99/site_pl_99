package com.example.site_pl_99.entity;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "videos_news")
@RequiredArgsConstructor
public class VideoNewsEntity extends BaseEntity {
    @Column(name = "file_name", nullable = false, unique = true)
    private String fileName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "news_id", referencedColumnName = "id")
    private NewsEntity newsEntity;
    @Column(name = "date_create")
    private LocalDateTime dateCreated;
    @Column(name = "date_updated")
    private LocalDateTime dateUpdated;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_create", referencedColumnName = "id")
    private UserEntity userCreated;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_updated", referencedColumnName = "id")
    private UserEntity userUpdated;
    @Column(name = "status_deleted")
    private Boolean statusDeleted;

    @PrePersist
    public void prePersist() {
        dateCreated = LocalDateTime.now();
        dateUpdated = LocalDateTime.now();
        statusDeleted = false;
    }

    public String getFileName() {
        return fileName;
    }

    public VideoNewsEntity setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public NewsEntity getNewsEntity() {
        return newsEntity;
    }

    public VideoNewsEntity setNewsEntity(NewsEntity newsEntity) {
        this.newsEntity = newsEntity;
        return this;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public VideoNewsEntity setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public VideoNewsEntity setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
        return this;
    }

    public UserEntity getUserCreated() {
        return userCreated;
    }

    public VideoNewsEntity setUserCreated(UserEntity userCreated) {
        this.userCreated = userCreated;
        return this;
    }

    public UserEntity getUserUpdated() {
        return userUpdated;
    }

    public VideoNewsEntity setUserUpdated(UserEntity userUpdated) {
        this.userUpdated = userUpdated;
        return this;
    }

    public Boolean getStatusDeleted() {
        return statusDeleted;
    }

    public VideoNewsEntity setStatusDeleted(Boolean statusDeleted) {
        this.statusDeleted = statusDeleted;
        return this;
    }
}
