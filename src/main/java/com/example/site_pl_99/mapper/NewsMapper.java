package com.example.site_pl_99.mapper;

import com.example.site_pl_99.dto.NewsDtoRequest;
import com.example.site_pl_99.dto.NewsDtoResponse;
import com.example.site_pl_99.entity.ImageNewsEntity;
import com.example.site_pl_99.entity.NewsEntity;

import java.util.List;
import java.util.stream.Collectors;

public class NewsMapper {
    public static NewsEntity toEntity(NewsDtoRequest newsDtoRequest) {
        return new NewsEntity().setTitleRu(newsDtoRequest.getTitle())
                .setTitleKg(newsDtoRequest.getTitle())
                .setSubTitleRu(newsDtoRequest.getSubTitle())
                .setSubTitleKg(newsDtoRequest.getSubTitle())
                .setDescriptionRu(newsDtoRequest.getDescription())
                .setDescriptionKg(newsDtoRequest.getDescription());
    }
    public static NewsDtoResponse toResponse(NewsEntity newsEntity) {
        List<Long> imageIds = newsEntity.getImageNewsEntityList() == null
                ? List.of()
                : newsEntity.getImageNewsEntityList().stream()
                .map(ImageNewsEntity::getId)
                .collect(Collectors.toList());

        return new NewsDtoResponse().setTitleRu(newsEntity.getTitleRu())
                .setTitleKg(newsEntity.getTitleKg())
                .setSubTitleRu(newsEntity.getSubTitleRu())
                .setSubTitleKg(newsEntity.getSubTitleKg())
                .setDescriptionRu(newsEntity.getDescriptionRu())
                .setDescriptionKg(newsEntity.getDescriptionKg())
                .setDateCreated(newsEntity.getDateCreated())
                .setActiveNews(newsEntity.getActive())
                .setImageSmallId(newsEntity.getImageNewsSmallEntity().getId())
                .setImageFullId(newsEntity.getImageNewsFullEntity().getId())
                .setImages(imageIds)
                .setVideoId(newsEntity.getVideoNewsEntity().getId());
    }
}
