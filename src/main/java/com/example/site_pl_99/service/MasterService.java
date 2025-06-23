package com.example.site_pl_99.service;

import com.example.site_pl_99.entity.MasterEntity;
import com.example.site_pl_99.enums.Active;

import java.time.LocalDate;
import java.util.List;

public interface MasterService extends BaseService<MasterEntity> {
    MasterEntity getFullName(String fullName);
    List<MasterEntity> getAllMastersContentName(String fullName);
    List<MasterEntity> getAllMastersByDateBerth(LocalDate dateBerth);
    List<MasterEntity> getAllMastersByStatusActive(Active status);
    List<MasterEntity> getAllMastersByProfession(String department);
    List<MasterEntity> getAllMastersByDateEmployment(LocalDate dateEmployment);
    List<MasterEntity> getAllMastersByDateDismissal(LocalDate dateDismissal);

}
