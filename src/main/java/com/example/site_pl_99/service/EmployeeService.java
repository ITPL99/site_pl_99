package com.example.site_pl_99.service;

import com.example.site_pl_99.entity.EmployeeEntity;
import com.example.site_pl_99.enums.Active;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService extends BaseService<EmployeeEntity> {
    EmployeeEntity getFullName(String fullName);
    List<EmployeeEntity> searchByName(String namePart);
    List<EmployeeEntity> getByDateBerth(LocalDate dateBerth);
    List<EmployeeEntity> getByStatusActive(Active status);
    List<EmployeeEntity> getByDepartment(String department);
    List<EmployeeEntity> getByDateEmployment(LocalDate dateEmployment);
    List<EmployeeEntity> getByDateDismissal(LocalDate dateDismissal);
    EmployeeEntity update(EmployeeEntity employeeEntity);
}
