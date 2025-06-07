package com.example.site_pl_99.mapper;

import com.example.site_pl_99.dto.NewsDtoRequest;
import com.example.site_pl_99.dto.NewsDtoResponse;
import com.example.site_pl_99.entity.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class NewsMapper {

    public static NewsEntity toNewsEntity(NewsDtoRequest newsDtoRequest, UserEntity userEntity) {
        return new NewsEntity().setTitle(newsDtoRequest.getTitle())
                .setDescription(newsDtoRequest.getDescription())
                .setUser(userEntity)
                .setUserCreated(userEntity)
                .setUserUpdated(userEntity);
    }

    public static NewsDtoResponse toNewsDtoResponse(NewsEntity newsEntity) {
        NewsDtoResponse newsDtoResponse = new NewsDtoResponse()
                .setId(newsEntity.getId())
                .setTitle(newsEntity.getTitle())
                .setDescription(newsEntity.getDescription())
                .setDateCreated(newsEntity.getDateCreated())
                .setDateUpdated(newsEntity.getDateUpdated())
                .setAuthorId(newsEntity.getUser().getId())
                .setUserIdUpdated(newsEntity.getUserUpdated().getId());
                if(Objects.nonNull(newsEntity.getImages())) newsDtoResponse.setImagesId(newsEntity.getImages().stream().map(BaseEntity::getId).toList());
                if(Objects.nonNull(newsEntity.getVideos())) newsDtoResponse.setVideosId(newsEntity.getVideos().stream().map(BaseEntity::getId).toList());
                return newsDtoResponse;
    }

    public static List<NewsDtoResponse> toNewsDtoResponseList(List<NewsEntity> newsEntities) {
        return newsEntities.stream().map(NewsMapper::toNewsDtoResponse).collect(Collectors.toList());
    }
}
