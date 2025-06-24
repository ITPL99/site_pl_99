package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.entity.NewsEntity;
import com.example.site_pl_99.enums.Active;
import com.example.site_pl_99.repository.NewsRepository;
import com.example.site_pl_99.service.NewsService;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;

    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public NewsEntity getTitle(String title) {
        return newsRepository.findByTitleRuOrTitleKg(title,title).orElseThrow(() -> new NotFoundException("Не найден"));
    }

    @Override
    public List<NewsEntity> getAllContentTitle(String title) {
        return newsRepository.findAllByTitleRuContainingOrTitleKgContaining(title, title).orElseThrow(() -> new NotFoundException("Не найден"));
    }

    @Override
    public List<NewsEntity> getAllContentSubTitle(String subTitle) {
        return newsRepository.findAllBySubTitleRuOrSubTitleKg(subTitle,subTitle).orElseThrow(() -> new NotFoundException("Не найден"));
    }

    @Override
    public List<NewsEntity> getAllActiveStatus(Active status) {
        return newsRepository.findAllByActive(status).orElseThrow(() -> new NotFoundException("Не найден"));
    }

    @Override
    public NewsEntity getById(Long id) {
        return newsRepository.findById(id).orElseThrow(() -> new NotFoundException("Не найден"));
    }

    @Override
    public NewsEntity save(NewsEntity entity) {
        return newsRepository.save(entity);
    }

    @Override
    public List<NewsEntity> getAll() {
        return newsRepository.findAll().stream().filter(n -> n.getActive() == Active.ACTIVE).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        NewsEntity newsEntity = getById(id);
        newsEntity.setActive(Active.DELETED);
        newsRepository.save(newsEntity);
    }
}
