package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.entity.News;
import com.example.site_pl_99.repository.NewsRepository;
import com.example.site_pl_99.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    private final NewsRepository repository;

    @Autowired
    public NewsServiceImpl(NewsRepository repository) {
        this.repository = repository;
    }

    @Override
    public News save(News news) {
        repository.save(news);
        return news;
    }

    @Override
    public List<News> getAll() {
        return repository.findAll();
    }

    @Override
    public News findNewsById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Такой новости не найдено"));
    }
}
