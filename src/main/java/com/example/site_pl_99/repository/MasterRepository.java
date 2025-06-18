package com.example.site_pl_99.repository;


import com.example.site_pl_99.entity.ImageEntity;
import com.example.site_pl_99.entity.MasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterRepository extends JpaRepository<MasterEntity, Long> {
}
