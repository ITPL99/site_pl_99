package com.example.site_pl_99.service;

import com.example.site_pl_99.dto.NewsDtoRequest;
import com.example.site_pl_99.entity.NewsEntity;
import com.example.site_pl_99.entity.UserEntity;

import java.util.List;

public interface NewsService {
    NewsEntity saveNews(NewsDtoRequest newsDtoRequest, UserEntity user);
    NewsEntity saveNews(NewsEntity newsEntity);
    NewsEntity getNewsId(Long id);
    List<NewsEntity> getAllNews();
   List<NewsEntity> getNewsByUser(UserEntity user);
}
