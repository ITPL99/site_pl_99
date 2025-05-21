package com.example.site_pl_99.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "videos_news")
public class Video extends BaseEntity{

    @ManyToOne()
    private News news;

    public Video() {
    }

    public Video(Long id, News news) {
        this.id = id;
        this.news = news;
    }

    public News getNews() {
        return news;
    }

    public Video setNews(News news) {
        this.news = news;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Video setId(Long id) {
        this.id = id;
        return this;
    }

}
