package com.example.site_pl_99.mapper;

import com.example.site_pl_99.dto.NewsDtoPreviewResponse;
import com.example.site_pl_99.dto.NewsDtoRequest;
import com.example.site_pl_99.dto.NewsDtoResponse;
import com.example.site_pl_99.dto.NewsDtoUpdate;
import com.example.site_pl_99.entity.ImageEntity;
import com.example.site_pl_99.entity.NewsEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.List;
import java.util.stream.Collectors;

public class NewsMapper {

    private static final Logger log = LoggerFactory.getLogger(NewsMapper.class);

    public static NewsEntity toNewsEntity(NewsDtoRequest newsDtoRequest) {
        log.info("Пришло для того что бы превротить ДТО в Сущность системы {}",newsDtoRequest);
        NewsEntity newsEntity = new NewsEntity();
        newsEntity.setTitleKg(newsDtoRequest.getTitleKg());
        newsEntity.setTitleRu(newsDtoRequest.getTitleRu());
        newsEntity.setSubTitleRu(newsDtoRequest.getSubTitleRu());
        newsEntity.setSubTitleKg(newsDtoRequest.getSubTitleKg());
        newsEntity.setDescriptionKg(newsDtoRequest.getDescriptionKg());
        newsEntity.setDescriptionRu(newsDtoRequest.getDescriptionRu());
        if (newsDtoRequest.getImageSmall() != null) {
            newsEntity.setImageSmall(ImageMapper.mapDtoToEntity(newsDtoRequest.getImageSmall()));
        }
        if (newsDtoRequest.getImageFull() != null) {
            newsEntity.setImageFull(ImageMapper.mapDtoToEntity(newsDtoRequest.getImageFull()));
        }
        if (newsDtoRequest.getImagesFile() != null) {
            newsEntity.setImages(newsDtoRequest.getImagesFile().stream().map(ImageMapper::mapDtoToEntity).collect(Collectors.toList()));
        }
        if (newsDtoRequest.getVideoFileName() != null) {
            newsEntity.setVideo(VideoMapper.mapDtoToEntity(newsDtoRequest.getVideoFileName()));
        }

        return newsEntity;
    }
    public static NewsDtoResponse toNewsDtoResponse(NewsEntity newsEntity) {
        log.info("пришло для трансформации из сущности в ДТО {}", newsEntity);
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
        newsDtoResponse.setActive(newsEntity.getActive().name());
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

    public static List<NewsDtoPreviewResponse> toNewsDtoPreviewResponseList(List<NewsEntity> newsEntityList) {
        return newsEntityList.stream().map(NewsMapper::toNewsDtoPreviewResponse).collect(Collectors.toList());
    }

    public static NewsEntity mapToNewsEntityFromUpdate(NewsDtoUpdate newsDtoUpdate){
        NewsEntity newsEntity = new NewsEntity();
        newsEntity.setTitleRu(newsDtoUpdate.getTitleRu())
                .setTitleKg(newsDtoUpdate.getTitleKg())
                .setSubTitleRu(newsDtoUpdate.getSubTitleRu())
                .setSubTitleKg(newsDtoUpdate.getSubTitleKg())
                .setDescriptionRu(newsDtoUpdate.getDescriptionRu())
                .setDescriptionKg(newsDtoUpdate.getDescriptionKg());
        if(newsDtoUpdate.getImageSmall() != null) newsEntity.setImageSmall(ImageMapper.mapDtoToEntity(newsDtoUpdate.getImageSmall()));
        if(newsDtoUpdate.getImageFull() != null) newsEntity.setImageFull(ImageMapper.mapDtoToEntity(newsDtoUpdate.getImageFull()));
        if(newsDtoUpdate.getVideo() != null) newsEntity.setVideo(VideoMapper.mapDtoToEntity(newsDtoUpdate.getVideo()));
        if(newsDtoUpdate.getImages() != null) newsEntity.setImages(newsDtoUpdate.getImages().stream().map(ImageMapper::mapDtoToEntity).collect(Collectors.toList()));
        return newsEntity;
    }
}
