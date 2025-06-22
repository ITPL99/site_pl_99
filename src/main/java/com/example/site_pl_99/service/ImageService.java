package com.example.site_pl_99.service;

import com.example.site_pl_99.entity.ImageEntity;

public interface ImageService{
    ImageEntity getById(Long id);
    ImageEntity getByFileName(String fileName);
    ImageEntity save(ImageEntity entity);
}
