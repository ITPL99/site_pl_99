package com.example.site_pl_99.service;

import com.example.site_pl_99.entity.NewsEntity;
import com.example.site_pl_99.enums.Active;

import java.util.List;
import java.util.Set;

public interface NewsService extends BaseService<NewsEntity> {
    NewsEntity getTitle(String title);
    List<NewsEntity> getAllContentTitle(String title);
    List<NewsEntity> getAllContentSubTitle(String subTitle);
    List<NewsEntity> getAllActiveStatus(Active status);
}
