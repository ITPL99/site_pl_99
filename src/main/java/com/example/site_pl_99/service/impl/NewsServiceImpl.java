package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.entity.NewsEntity;
import com.example.site_pl_99.enums.Active;
import com.example.site_pl_99.excaption.IncorectInputException;
import com.example.site_pl_99.excaption.NewsIsNotFoundException;
import com.example.site_pl_99.excaption.UniquenessViolationException;
import com.example.site_pl_99.repository.NewsRepository;
import com.example.site_pl_99.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class NewsServiceImpl implements NewsService {
    private static final Logger log = LoggerFactory.getLogger(NewsServiceImpl.class);
    private final NewsRepository newsRepository;

    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public NewsEntity addNews(NewsEntity newsEntity) {
        log.info("Пришли данные в бизнес логику {}",newsEntity);
        try {
            if (isNullOrEmpty(newsEntity.getTitleRu()) && isNullOrEmpty(newsEntity.getTitleKg()))
                throw new IncorectInputException("error.emptyTitle");
            if (isNullOrEmpty(newsEntity.getDescriptionRu()) && isNullOrEmpty(newsEntity.getDescriptionKg()))
                throw new IncorectInputException("error.emptyDescription");
            if (isNullOrEmpty(newsEntity.getSubTitleRu()) && isNullOrEmpty(newsEntity.getSubTitleKg()))
                throw new IncorectInputException("error.emptySubtitle");
            return newsRepository.save(newsEntity);
        }catch (DataIntegrityViolationException e){
            throw new UniquenessViolationException(e.getMessage());
        }
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
        return newsRepository.findAllByActive(Active.valueOf(active));
    }

    @Override
    public void deleteById(Long id) {
        NewsEntity newsEntity = newsRepository.findById(id).orElseThrow(() -> new NewsIsNotFoundException("error.findNews"));
        newsEntity.setActive(Active.DELETED);
        newsRepository.save(newsEntity);
    }

    @Override
    public NewsEntity updateNews(Long id,NewsEntity news) {
        NewsEntity newsEntity = newsRepository.findById(id).orElseThrow(() -> new NewsIsNotFoundException("error.findNews"));
        if(isNullOrEmpty(news.getTitleRu()) && isNullOrEmpty(news.getTitleKg())) throw new IncorectInputException("error.emptyTitle");
        if(isNullOrEmpty(news.getDescriptionRu()) && isNullOrEmpty(news.getDescriptionKg())) throw new IncorectInputException("error.emptyDescription");
        if(isNullOrEmpty(news.getSubTitleRu()) && isNullOrEmpty(news.getSubTitleKg())) throw new IncorectInputException("error.emptySubtitle");
        if(news.getImageSmall() != null) newsEntity.setImageSmall(news.getImageSmall());
        if(news.getImageFull() != null) newsEntity.setImageFull(news.getImageFull());
        if(news.getImages() != null) newsEntity.setImages(news.getImages());
        if(news.getVideo() != null) newsEntity.setVideo(news.getVideo());
        return newsRepository.save(newsEntity);
    }

    @Override
    public NewsEntity getById(Long id) {
        return newsRepository.findById(id).orElseThrow(() -> new NewsIsNotFoundException("error.findNews"));
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
