package com.example.site_pl_99.mapper;

import com.example.site_pl_99.dto.NewsDtoPreviewResponse;
import com.example.site_pl_99.dto.NewsDtoRequest;
import com.example.site_pl_99.dto.NewsDtoResponse;
import com.example.site_pl_99.entity.ImageEntity;
import com.example.site_pl_99.entity.NewsEntity;
import com.example.site_pl_99.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

public class NewsMapper {

    public static NewsEntity toNewsEntity(NewsDtoRequest newsDtoRequest) {
        NewsEntity newsEntity = new NewsEntity();
        newsEntity.setTitleKg(newsDtoRequest.getTitleKg());
        newsEntity.setTitleRu(newsDtoRequest.getTitleRu());
        newsEntity.setSubTitleRu(newsDtoRequest.getSubTitleRu());
        newsEntity.setSubTitleKg(newsDtoRequest.getSubTitleKg());
        newsEntity.setDescriptionKg(newsDtoRequest.getDescriptionKg());
        newsEntity.setDescriptionRu(newsDtoRequest.getDescriptionRu());
        newsEntity.setImageSmall(ImageMapper.mapDtoToEntity(newsDtoRequest.getImageSmall()));
        newsEntity.setImageFull(ImageMapper.mapDtoToEntity(newsDtoRequest.getImageFull()));
        newsEntity.setImages(newsDtoRequest.getImagesFile().stream().map(ImageMapper::mapDtoToEntity).collect(Collectors.toList()));
        newsEntity.setVideo(VideoMapper.mapDtoToEntity(newsDtoRequest.getVideoFileName()));

        return newsEntity;
    }
    public static NewsDtoResponse toNewsDtoResponse(NewsEntity newsEntity) {
        NewsDtoResponse newsDtoResponse = new NewsDtoResponse();
        newsDtoResponse.setId(newsEntity.getId());

        if(LocaleContextHolder.getLocale().getLanguage().equalsIgnoreCase("ru")){
            newsDtoResponse.setTitle(newsEntity.getTitleRu());
            newsDtoResponse.setSubTitle(newsEntity.getSubTitleRu());
            newsDtoResponse.setDescription(newsEntity.getSubTitleRu());
        }else{
            newsDtoResponse.setTitle(newsEntity.getTitleKg());
            newsDtoResponse.setSubTitle(newsEntity.getSubTitleKg());
            newsDtoResponse.setDescription(newsEntity.getDescriptionKg());
        }
        newsDtoResponse.setDateCreate(newsEntity.getDateCreate());
        if(newsEntity.getImageSmall() != null) newsDtoResponse.setImageSmallFileName(newsEntity.getImageSmall().getFileName());
        if(newsEntity.getImageFull() != null) newsDtoResponse.setImageFullFileName(newsEntity.getImageFull().getFileName());
        if(newsEntity.getImages() != null) newsDtoResponse.setImagesFilesName(newsEntity.getImages().stream().map(ImageEntity::getFileName).collect(Collectors.toList()));
        if(newsEntity.getVideo() != null) newsDtoResponse.setVideoFile(newsEntity.getVideo().getFileName());
        return newsDtoResponse;
    }

    public static NewsDtoPreviewResponse toNewsDtoPreviewResponse(NewsEntity newsEntity) {
        NewsDtoPreviewResponse newsDtoPreviewResponse = new NewsDtoPreviewResponse();
        newsDtoPreviewResponse.setId(newsEntity.getId());
        if(LocaleContextHolder.getLocale().getLanguage().equalsIgnoreCase("ru")){
            newsDtoPreviewResponse.setTitle(newsEntity.getTitleKg());
            newsDtoPreviewResponse.setSubTitle(newsEntity.getSubTitleKg());
        }else{
            newsDtoPreviewResponse.setTitle(newsEntity.getTitleKg());
            newsDtoPreviewResponse.setSubTitle(newsEntity.getSubTitleKg());
        }
        newsDtoPreviewResponse.setDateCreated(newsEntity.getDateCreate());
        if(newsEntity.getImageSmall() != null) newsDtoPreviewResponse.setImageSmall(newsEntity.getImageSmall().getFileName());
        return newsDtoPreviewResponse;
    }
}
