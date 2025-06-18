package com.example.site_pl_99.service;


import com.example.site_pl_99.entity.VideoEntity;

public interface VideoService extends BaseService<VideoEntity> {
    VideoEntity getByFileName(String fileName);
}
