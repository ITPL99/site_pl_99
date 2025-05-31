package com.example.site_pl_99.service;

import com.example.site_pl_99.entity.AvatarWorkerEntity;
import com.example.site_pl_99.entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface AvatarWorkerService {
    AvatarWorkerEntity upload(MultipartFile file, Long workerId, UserEntity user);
    String getContentType(Long id);
    InputStream stream(Long id);
}
