package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.entity.ImageEntity;
import com.example.site_pl_99.excaption.ImageNotFoundException;
import com.example.site_pl_99.repository.ImageRepository;
import com.example.site_pl_99.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public ImageEntity getById(Long id) {
        return imageRepository.findById(id).orElseThrow(() -> new ImageNotFoundException("error.isNotFoundImage"));
    }

    @Override
    public ImageEntity getByFileName(String fileName) {
        return imageRepository.findByFileName(fileName).orElseThrow(() -> new ImageNotFoundException("error.isNotFoundImage"));
    }

    @Override
    public ImageEntity save(ImageEntity entity) {
        return imageRepository.save(entity);
    }
}
