package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.entity.NewsEntity;
import com.example.site_pl_99.enums.Active;
import com.example.site_pl_99.excaption.NewsIsNotFoundException;
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
    public NewsEntity addNews(NewsEntity newsEntity) {
        return newsRepository.save(newsEntity);
    }

    @Override
    public List<NewsEntity> getAll() {
        return newsRepository.findAll().stream().filter(newsEntity -> newsEntity.getActive().equals(Active.ACTIVE)).collect(Collectors.toList());
    }

    @Override
    public List<NewsEntity> getAllFull() {
        return newsRepository.findAll();
    }

    @Override
    public NewsEntity getByTitle(String title) {
        return newsRepository.findByTitleRuOrTitleKg(title, title).orElseThrow(() -> new NewsIsNotFoundException("error.findNews"));
    }

    //TODO: Нужно подключать QueryDSL, сделаю вечером
    @Override
    public List<NewsEntity> getAllByContentTitle(String title) {
        return null;
    }

    //TODO: Нужно подключать QueryDSL, сделаю вечером
    @Override
    public List<NewsEntity> getAllByContentSubTitle(String subTitle) {
        return null;
    }

    @Override
    public List<NewsEntity> getAllByActiveStatus(String active) {
        return newsRepository.findAllByActive(Active.valueOf(active)).orElseThrow(() -> new NewsIsNotFoundException("error.findNews"));
    }

    @Override
    public void deleteById(Long id) {
        NewsEntity newsEntity = newsRepository.findById(id).orElseThrow(() -> new NewsIsNotFoundException("error.findNews"));
        newsEntity.setActive(Active.DELETED);
        newsRepository.save(newsEntity);
    }

    @Override
    public NewsEntity getById(Long id) {
        return newsRepository.findById(id).orElseThrow(() -> new NewsIsNotFoundException("error.findNews"));
    }
}
