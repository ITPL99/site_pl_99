package com.example.site_pl_99.repository;

import com.example.site_pl_99.entity.CourseEntity;
import com.example.site_pl_99.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}
