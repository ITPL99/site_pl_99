package com.example.site_pl_99.service;

import com.example.site_pl_99.entity.TeacherEntity;
import com.example.site_pl_99.enums.Active;

import java.time.LocalDate;
import java.util.List;

public interface TeacherService extends BaseService<TeacherEntity> {
    TeacherEntity getFullName(String fullName);
    List<TeacherEntity> getAllMastersContentName(String fullName);
    List<TeacherEntity> getAllMastersByDateBerth(LocalDate dateBerth);
    List<TeacherEntity> getAllMastersByStatusActive(Active status);
    List<TeacherEntity> getAllMastersByPortfolio(String department);
    List<TeacherEntity> getAllMastersByDateEmployment(LocalDate dateEmployment);
    List<TeacherEntity> getAllMastersByDateDismissal(LocalDate dateDismissal);


}
