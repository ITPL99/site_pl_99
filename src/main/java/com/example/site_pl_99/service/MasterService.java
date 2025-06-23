package com.example.site_pl_99.service;

import com.example.site_pl_99.entity.MasterEntity;
import com.example.site_pl_99.enums.Active;

import java.time.LocalDate;
import java.util.List;

public interface MasterService extends BaseService<MasterEntity> {
    MasterEntity getFullName(String fullName);
    List<MasterEntity> searchByName(String namePart);
    List<MasterEntity> getAllActiveStatus(Active status);
    List<MasterEntity> getByDateBerth(LocalDate dateBerth);
    List<MasterEntity> getByProfession(String department);
    List<MasterEntity> getByDateEmployment(LocalDate dateEmployment);
    List<MasterEntity> getByDateDismissal(LocalDate dateDismissal);
    MasterEntity update(MasterEntity entity);
}
