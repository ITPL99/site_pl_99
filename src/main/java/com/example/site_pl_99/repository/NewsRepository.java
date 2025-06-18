package com.example.site_pl_99.repository;


import com.example.site_pl_99.entity.MasterEntity;
import com.example.site_pl_99.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<NewsEntity, Long> {
}
