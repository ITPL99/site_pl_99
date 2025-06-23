package com.example.site_pl_99.repository;


import com.example.site_pl_99.entity.TeacherEntity;
import com.example.site_pl_99.enums.Active;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {
    Optional<TeacherEntity> findByFullName(String fullName);
    Optional<List<TeacherEntity>> findByFullNameContaining(String fullName);
    Optional<List<TeacherEntity>> findAllByDateBerth(LocalDate dateBerth);
    Optional<List<TeacherEntity>> findAllByActive(Active active);
    Optional<List<TeacherEntity>> findAllByLinkPortfolio(String linkPortfolio);
    Optional<List<TeacherEntity>> findAllByDateEmployment(LocalDate dateEmployment);
    Optional<List<TeacherEntity>> findAllByDateDismissal(LocalDate dateDismissal);
}
