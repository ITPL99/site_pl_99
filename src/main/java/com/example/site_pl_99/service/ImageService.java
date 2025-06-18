package com.example.site_pl_99.service;

import com.example.site_pl_99.entity.ImageEntity;

public interface ImageService extends BaseService<ImageEntity> {
    ImageEntity getByFileName(String fileName);
}
