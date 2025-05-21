package com.example.site_pl_99.dto;

import com.example.site_pl_99.entity.News;

public class VideoNewsDTORequestRegister {
    private Long id;
    private String name;
    private News news;

    public VideoNewsDTORequestRegister() {
    }

    public VideoNewsDTORequestRegister(Long id, String name, News news) {
        this.id = id;
        this.name = name;
        this.news = news;
    }

    public Long getId() {
        return id;
    }

    public VideoNewsDTORequestRegister setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public VideoNewsDTORequestRegister setName(String name) {
        this.name = name;
        return this;
    }

    public News getNews() {
        return news;
    }

    public VideoNewsDTORequestRegister setNews(News news) {
        this.news = news;
        return this;
    }
}
