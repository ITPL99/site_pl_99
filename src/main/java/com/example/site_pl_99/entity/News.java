package com.example.site_pl_99.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "news")
public class News extends BaseEntity{

    @Column
    private String title;
    @Column
    private String description;
    @Column
    private LocalDateTime dateCreated;
    @OneToOne(fetch = FetchType.LAZY)
    private UserEntity user;

    public News() {
    }

    public News(Long id,
                String title,
                String description,
                LocalDateTime dateCreated,
                UserEntity user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dateCreated = dateCreated;
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public News setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public News setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public News setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public News setUser(UserEntity user) {
        this.user = user;
        return this;
    }
}
