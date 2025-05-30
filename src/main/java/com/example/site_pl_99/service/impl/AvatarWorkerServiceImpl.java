package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.entity.AvatarWorkerEntity;
import com.example.site_pl_99.mapper.WorkerMapper;
import com.example.site_pl_99.repository.AvatarRepository;
import com.example.site_pl_99.service.AvatarWorkerService;
import com.example.site_pl_99.service.MinIoService;
import com.example.site_pl_99.service.UserService;
import com.example.site_pl_99.service.WorkerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public class AvatarWorkerServiceImpl implements AvatarWorkerService {
    private final AvatarRepository avatarRepository;
    private final WorkerService workerService;
    private final UserService userService;
    private final MinIoService minIoService;
    @Value("${minio.bucket.name.avatar}")
    private String bucketName;

    public AvatarWorkerServiceImpl(AvatarRepository avatarRepository, WorkerService workerService, UserService userService, MinIoService minIoService) {
        this.avatarRepository = avatarRepository;
        this.workerService = workerService;
        this.userService = userService;
        this.minIoService = minIoService;
    }


    @Override
    public AvatarWorkerEntity upload(MultipartFile file, Long workerId, Long userID) {
        try {
            if (minIoService.fileExists(bucketName, file.getOriginalFilename())) {
                throw new RuntimeException("Файл с таким именем уже существует. Переименуйте файл и попробуйте снова");
            }
            AvatarWorkerEntity avatarWorkerEntity = new AvatarWorkerEntity()
                    .setWorker(WorkerMapper.toWorkerEntity(workerService.getWorkerId(workerId), userService.getById(userID)))
                    .setUser(userService.getById(userID))
                    .setFileName(file.getOriginalFilename());
            minIoService.upload(file, bucketName);
            avatarRepository.save(avatarWorkerEntity);
            return avatarWorkerEntity;
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public InputStream stream(Long id) {
        AvatarWorkerEntity avatarWorker = avatarRepository.findById(id).orElseThrow(() -> new RuntimeException("Такого видео нет"));
        return minIoService.streamFile(bucketName, avatarWorker.getFileName());
    }

    @Override
    public String getContentType(Long id){
        AvatarWorkerEntity avatarWorker = avatarRepository.findById(id).orElseThrow(() -> new RuntimeException("Такого видео нет"));
        return minIoService.getContentType(bucketName, avatarWorker.getFileName());
    }
}
