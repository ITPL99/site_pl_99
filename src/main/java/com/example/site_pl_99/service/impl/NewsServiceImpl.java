package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.entity.News;
import com.example.site_pl_99.entity.UserEntity;
import com.example.site_pl_99.repository.NewsRepository;
import com.example.site_pl_99.repository.UserRepository;
import com.example.site_pl_99.service.NewsService;
import com.example.site_pl_99.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    private final NewsRepository repository;
    private final UserRepository userRepository;

    @Autowired
    public NewsServiceImpl(NewsRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public News save(News news) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        news.setUser(userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Не нашол юзера при создании новостей ")));
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
