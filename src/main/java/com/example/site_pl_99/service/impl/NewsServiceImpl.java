package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.dto.NewsDtoRequest;
import com.example.site_pl_99.entity.NewsEntity;
import com.example.site_pl_99.entity.UserEntity;
import com.example.site_pl_99.mapper.NewsMapper;
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
        return newsRepository.save(NewsMapper.toNewsEntity(newsDtoRequest, user));
    }

    @Override
    public NewsEntity getNewsId(Long id) {
        return newsRepository.findById(id).orElseThrow(() -> new RuntimeException("Нет такой новости"));
    }

    @Override
    public List<NewsEntity> getAllNews() {
        return newsRepository.findAll();
    }

    @Override
    public List<NewsEntity> getNewsByUser(UserEntity user) {
        return newsRepository.findNewsEntitiesByUser(user).orElseThrow(()-> new RuntimeException("Нет такой новости"));
    }
}
