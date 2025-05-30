package com.example.site_pl_99.service;

import com.example.site_pl_99.dto.NewsDtoRequest;
import com.example.site_pl_99.dto.NewsDtoResponse;
import com.example.site_pl_99.entity.UserEntity;

import java.util.List;

public interface NewsService {
    NewsDtoResponse saveNews(NewsDtoRequest newsDtoRequest, UserEntity user);
    NewsDtoResponse getNewsId(Long id);
    List<NewsDtoResponse> getAllNews();
    NewsDtoResponse getNewsByUser(Long userId);
}
