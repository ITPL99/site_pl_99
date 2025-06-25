package com.example.site_pl_99.repository;

import com.example.site_pl_99.entity.EmployeeEntity;
import com.example.site_pl_99.enums.Active;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    Optional<EmployeeEntity> findByFullName(String fullName);
    List<EmployeeEntity> findByFullNameContainingIgnoreCase(String name);
    List<EmployeeEntity> findByDateBerth(LocalDate dateBerth);
    List<EmployeeEntity> findByActive(Active active);
    List<EmployeeEntity> findByDepartmentRuIgnoreCaseOrDepartmentKgIgnoreCase(String depRu, String depKg);
    List<EmployeeEntity> findByDateEmployment(LocalDate dateEmployment);
    List<EmployeeEntity> findByDateDismissal(LocalDate dateDismissal);
}
