package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.dto.NewsDtoRequest;
import com.example.site_pl_99.dto.NewsDtoResponse;
import com.example.site_pl_99.entity.UserEntity;
import com.example.site_pl_99.service.NewsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Override
    public NewsDtoResponse saveNews(NewsDtoRequest newsDtoRequest, UserEntity user) {
        return null;
    }

    @Override
    public NewsDtoResponse getNewsId(Long id) {
        return null;
    }

    @Override
    public List<NewsDtoResponse> getAllNews() {
        return List.of();
    }

    @Override
    public NewsDtoResponse getNewsByUser(Long userId) {
        return null;
    }
}
