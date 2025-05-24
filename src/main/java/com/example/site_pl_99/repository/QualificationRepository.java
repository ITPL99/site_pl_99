package com.example.site_pl_99.repository;

import com.example.site_pl_99.entity.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QualificationRepository extends JpaRepository<Qualification, Long> {
    Optional<Qualification> findQualificationByTitle(String title);
}
