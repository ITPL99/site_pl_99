package com.example.site_pl_99.service;

import com.example.site_pl_99.entity.News;

import java.util.List;

public interface NewsService {
    News save(News news);
    List<News> getAll();
    News findNewsById(Long id);
}
