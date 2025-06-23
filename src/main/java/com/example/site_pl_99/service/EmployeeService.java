package com.example.site_pl_99.service;

import com.example.site_pl_99.entity.EmployeeEntity;
import com.example.site_pl_99.enums.Active;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService extends BaseService<EmployeeEntity> {
    EmployeeEntity getFullName(String fullName);
    List<EmployeeEntity> getAllEmployeesContentName(String fullName);
    List<EmployeeEntity> getAllEmployeesByDateBerth(LocalDate dateBerth);
    List<EmployeeEntity> getAllEmployeesByStatusActive(Active status);
    List<EmployeeEntity> getAllEmployeesByDepartment(String department);
    List<EmployeeEntity> getAllEmployeesByDateEmployment(LocalDate dateEmployment);
    List<EmployeeEntity> getAllEmployeesByDateDismissal(LocalDate dateDismissal);

    List<EmployeeEntity> getFullAll();
}
