package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.entity.VideoEntity;
import com.example.site_pl_99.excaption.VideoNotFoundException;
import com.example.site_pl_99.repository.VideoRepository;
import com.example.site_pl_99.service.VideoService;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl implements VideoService {
    private final VideoRepository videoRepository;

    public VideoServiceImpl(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Override
    public VideoEntity getById(Long id) {
        return videoRepository.findById(id).orElseThrow(() -> new VideoNotFoundException("error.isNotFoundVideo"));
    }

    @Override
    public VideoEntity getByFileName(String fileName) {
        return videoRepository.findByFileName(fileName).orElseThrow(() -> new VideoNotFoundException("error.isNotFoundVideo"));
    }

    @Override
    public VideoEntity save(VideoEntity entity) {
        return videoRepository.save(entity);
    }

}
