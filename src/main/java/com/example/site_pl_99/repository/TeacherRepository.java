package com.example.site_pl_99.repository;


import com.example.site_pl_99.entity.NewsEntity;
import com.example.site_pl_99.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {
}
