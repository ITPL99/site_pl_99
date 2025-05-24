package com.example.site_pl_99.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "videos_news")
public class VideoNews extends BaseEntity{

    @Column
    private String name;
    @ManyToOne()
    private News news;

    public VideoNews() {
    }

    public VideoNews(Long id,String name, News news) {
        this.id = id;
        this.name = name;
        this.news = news;
    }

    public String getName() {
        return name;
    }

    public VideoNews setName(String name) {
        this.name = name;
        return this;
    }

    public News getNews() {
        return news;
    }

    public VideoNews setNews(News news) {
        this.news = news;
        return this;
    }
}
