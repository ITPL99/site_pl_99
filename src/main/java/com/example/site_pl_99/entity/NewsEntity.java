package com.example.site_pl_99.entity;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "news")
@RequiredArgsConstructor
public class NewsEntity extends BaseEntity {
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "date_created")
    private LocalDateTime dateCreated;
    @Column(name = "date_updated")
    private LocalDateTime dateUpdated;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_create", referencedColumnName = "id")
    private UserEntity userCreated;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_updated", referencedColumnName = "id")
    private UserEntity userUpdated;
    @OneToMany(mappedBy = "newsEntity",fetch = FetchType.EAGER)
    private List<ImageNewsEntity> images;
    @OneToMany(mappedBy = "newsEntity",fetch = FetchType.EAGER)
    private List<VideoNewsEntity> videos;
    @Column(name = "status_deleted")
    private Boolean statusDeleted;

    @PrePersist
    public void prePersist() {
        dateCreated = LocalDateTime.now();
        dateUpdated = LocalDateTime.now();
        statusDeleted = false;
    }

    public String getTitle() {
        return title;
    }

    public NewsEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public NewsEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public NewsEntity setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public NewsEntity setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public NewsEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public UserEntity getUserCreated() {
        return userCreated;
    }

    public NewsEntity setUserCreated(UserEntity userCreated) {
        this.userCreated = userCreated;
        return this;
    }

    public UserEntity getUserUpdated() {
        return userUpdated;
    }

    public NewsEntity setUserUpdated(UserEntity userUpdated) {
        this.userUpdated = userUpdated;
        return this;
    }

    public Boolean getStatusDeleted() {
        return statusDeleted;
    }

    public NewsEntity setStatusDeleted(Boolean statusDeleted) {
        this.statusDeleted = statusDeleted;
        return this;
    }

    public List<ImageNewsEntity> getImages() {
        return images;
    }

    public NewsEntity setImages(List<ImageNewsEntity> images) {
        this.images = images;
        return this;
    }

    public List<VideoNewsEntity> getVideos() {
        return videos;
    }

    public NewsEntity setVideos(List<VideoNewsEntity> videos) {
        this.videos = videos;
        return this;
    }


}
