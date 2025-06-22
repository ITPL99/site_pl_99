package com.example.site_pl_99.service;

import com.example.site_pl_99.entity.VideoEntity;

public interface VideoService {
    VideoEntity getById(Long id);
    VideoEntity getByFileName(String fileName);
    VideoEntity save(VideoEntity entity);
}
