package com.example.site_pl_99.repository;

import com.example.site_pl_99.entity.VideoNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<VideoNews, Long> {
}
