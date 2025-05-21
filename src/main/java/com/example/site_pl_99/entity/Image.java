package com.example.site_pl_99.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "images_news")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    private News news;

    public Image() {
    }

    public Image(Long id, News news) {
        this.id = id;
        this.news = news;
    }

    public News getNews() {
        return news;
    }

    public Image setNews(News news) {
        this.news = news;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Image setId(Long id) {
        this.id = id;
        return this;
    }
}
