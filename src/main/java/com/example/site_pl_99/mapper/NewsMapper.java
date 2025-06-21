package com.example.site_pl_99.mapper;

import com.example.site_pl_99.dto.NewsDtoRequest;
import com.example.site_pl_99.dto.NewsDtoResponse;
import com.example.site_pl_99.dto.NewsDtoResponseKg;
import com.example.site_pl_99.entity.NewsEntity;

public class NewsMapper {
    public static NewsEntity toNewsEntity(NewsDtoRequest newsDtoRequest) {
        NewsEntity newsEntity = new NewsEntity();
        newsEntity.setTitleKg(newsDtoRequest.getTitle());
        newsEntity.setTitleRu(newsDtoRequest.getTitle());
        newsEntity.setSubTitleRu(newsDtoRequest.getSubTitle());
        newsEntity.setSubTitleKg(newsDtoRequest.getSubTitle());
        return newsEntity;
    }
    public static NewsDtoResponse toNewsDtoResponse(NewsEntity newsEntity) {
        NewsDtoResponse newsDtoResponse = new NewsDtoResponse();
        newsDtoResponse.setTitle(newsEntity.getTitleRu());
        newsDtoResponse.setSubTitle(newsEntity.getSubTitleRu());
        newsDtoResponse.setDescription(newsEntity.getSubTitleRu());
        newsDtoResponse.setDateCreate(newsEntity.getDateCreate());
        return newsDtoResponse;
    }
    public static NewsDtoResponseKg toNewsDtoResponseKg(NewsEntity newsEntity) {
        NewsDtoResponseKg newsDtoResponseKg = new NewsDtoResponseKg();
        newsDtoResponseKg.setTitle(newsEntity.getTitleKg());
        newsDtoResponseKg.setSubTitle(newsEntity.getSubTitleKg());
        newsDtoResponseKg.setDescription(newsEntity.getSubTitleKg());
        newsDtoResponseKg.setDateCreate(newsEntity.getDateCreate());
        return newsDtoResponseKg;
    }
}
