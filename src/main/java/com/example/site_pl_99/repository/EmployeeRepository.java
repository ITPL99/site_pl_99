package com.example.site_pl_99.repository;

import com.example.site_pl_99.entity.CourseEntity;
import com.example.site_pl_99.entity.EmployeeEntity;
import com.example.site_pl_99.enums.Active;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    Optional<EmployeeEntity> findByFullName(String fullName);
    Optional<List<EmployeeEntity>> findByFullNameContaining(String fullName);
    Optional<List<EmployeeEntity>> findAllByDateBerth(LocalDate dateBerth);
    Optional<List<EmployeeEntity>> findAllByDateEmployment(LocalDate dateEmployment);
    Optional<List<EmployeeEntity>> findAllByDateDismissal(LocalDate dateDismissal);
    Optional<List<EmployeeEntity>> findAllByActive(Active active);
    Optional<List<EmployeeEntity>> findAllByDepartmentKgOrDepartmentRu(String departmentKg, String departmentRu);



}
