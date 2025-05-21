package com.example.site_pl_99.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "images_news")
public class ImageNews extends BaseEntity{
    @Column()
    private String name;
    @ManyToOne()
    private News news;

    public ImageNews() {
    }

    public ImageNews(Long id, News news, String name) {
        this.name = name;
        this.id = id;
        this.news = news;
    }

    public String getName() {
        return name;
    }

    public ImageNews setName(String name) {
        this.name = name;
        return this;
    }

    public News getNews() {
        return news;
    }

    public ImageNews setNews(News news) {
        this.news = news;
        return this;
    }

    public Long getId() {
        return id;
    }

    public ImageNews setId(Long id) {
        this.id = id;
        return this;
    }
}
