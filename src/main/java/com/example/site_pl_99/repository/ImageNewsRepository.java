package com.example.site_pl_99.repository;

import com.example.site_pl_99.entity.ImageNewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageNewsRepository extends JpaRepository<ImageNewsEntity, Long> {

}
