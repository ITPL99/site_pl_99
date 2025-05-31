package com.example.site_pl_99.repository;

import com.example.site_pl_99.entity.CourseEntity;
import com.example.site_pl_99.entity.UserEntity;
import com.example.site_pl_99.entity.WorkerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
    Optional<List<CourseEntity>> findCourseEntitiesByWorker(WorkerEntity worker);
    Optional<List<CourseEntity>> findCourseEntitiesByUser(UserEntity user);
}
