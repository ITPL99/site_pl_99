package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.dto.NewsDtoRequest;
import com.example.site_pl_99.entity.NewsEntity;
import com.example.site_pl_99.entity.UserEntity;
import com.example.site_pl_99.repository.NewsRepository;
import com.example.site_pl_99.service.NewsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;

    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public NewsEntity saveNews(NewsDtoRequest newsDtoRequest, UserEntity user) {
        return null;
    }

    @Override
    public NewsEntity getNewsId(Long id) {
        return null;
    }

    @Override
    public List<NewsEntity> getAllNews() {
        return List.of();
    }

    @Override
    public NewsEntity getNewsByUser(Long userId) {
        return null;
    }
}
