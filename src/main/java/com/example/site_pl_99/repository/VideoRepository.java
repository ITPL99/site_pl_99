package com.example.site_pl_99.repository;

import com.example.site_pl_99.entity.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VideoRepository extends JpaRepository<VideoEntity, Long> {
    Optional<VideoEntity> findByFileName(String fileName);
}
