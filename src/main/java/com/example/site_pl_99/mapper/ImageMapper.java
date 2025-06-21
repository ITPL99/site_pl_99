package com.example.site_pl_99.mapper;

import com.example.site_pl_99.dto.ImageDto;
import com.example.site_pl_99.entity.ImageEntity;

public class ImageMapper {
    public static ImageEntity mapDtoToEntity(ImageDto image) {
        ImageEntity entity = new ImageEntity()
                .setFileName(image.getFileName());
        entity.setId(image.getId() != null? image.getId(): null);
        return entity;
    }

    public static ImageDto mapEntityToDto(ImageEntity entity) {
        return new ImageDto(entity);
    }
}
