package com.example.site_pl_99.mapper;

import com.example.site_pl_99.dto.VideoDto;
import com.example.site_pl_99.entity.VideoEntity;

public class VideoMapper {
    public static VideoEntity mapDtoToEntity(VideoDto dto) {
        VideoEntity entity = new VideoEntity();
        entity.setId(dto.getId());
        entity.setFileName(dto.getVideoFileName());
        return entity;
    }
}
