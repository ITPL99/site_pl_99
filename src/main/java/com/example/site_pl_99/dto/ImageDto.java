package com.example.site_pl_99.dto;

import com.example.site_pl_99.entity.ImageEntity;

public class ImageDto {
    private Long Id;
    private String fileName;

    public ImageDto(ImageEntity imageEntity) {
        Id = imageEntity.getId();
        fileName = imageEntity.getFileName();
    }

    public ImageDto() {
    }

    public String getFileName() {

        return fileName;
    }

    public Long getId() {
        return Id;
    }
}
