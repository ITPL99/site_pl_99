package com.example.site_pl_99.service;

import com.example.site_pl_99.entity.TeacherEntity;
import com.example.site_pl_99.enums.Active;

import java.time.LocalDate;
import java.util.List;

public interface TeacherService extends BaseService<TeacherEntity> {
    TeacherEntity getFullName(String fullName);
    List<TeacherEntity> searchByName(String namePart);
    List<TeacherEntity> getByDateBerth(LocalDate dateBerth);
    List<TeacherEntity> getByStatusActive(Active status);
    List<TeacherEntity> getByPortfolio(String department);
    List<TeacherEntity> getByDateEmployment(LocalDate dateEmployment);
    List<TeacherEntity> getDateDismissal(LocalDate dateDismissal);
    TeacherEntity update(TeacherEntity entity);

}
