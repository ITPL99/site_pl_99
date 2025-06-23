package com.example.site_pl_99.service;

import com.example.site_pl_99.entity.NewsEntity;
import com.example.site_pl_99.enums.Active;

import java.util.List;

public interface NewsService {
    NewsEntity addNews(NewsEntity newsEntity);
    List<NewsEntity> getAll();
    List<NewsEntity> getAllFull();
    NewsEntity getByTitle(String title);
    List<NewsEntity> getAllByContentTitle(String title);
    List<NewsEntity> getAllByContentSubTitle(String subTitle);
    List<NewsEntity> getAllByActiveStatus(String status);
    void deleteById(Long id);
    NewsEntity getById(Long id);
}
