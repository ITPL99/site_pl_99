package com.example.site_pl_99.repository;


import com.example.site_pl_99.entity.TeacherEntity;
import com.example.site_pl_99.enums.Active;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {
    Optional<TeacherEntity> findByFullName(String fullName);
    List<TeacherEntity> findByFullNameContaining(String fullName);
    List<TeacherEntity> findAllByDateBerth(LocalDate dateBerth);
    List<TeacherEntity> findAllByActive(Active active);
    List<TeacherEntity> findAllByLinkPortfolio(String linkPortfolio);
    List<TeacherEntity> findAllByDateEmployment(LocalDate dateEmployment);
    List<TeacherEntity> findAllByDateDismissal(LocalDate dateDismissal);
}
