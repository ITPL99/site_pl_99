package com.example.site_pl_99.mapper;

import com.example.site_pl_99.dto.EmployeeDtoRequest;
import com.example.site_pl_99.dto.EmployeeDtoResponseKg;
import com.example.site_pl_99.dto.EmployeeDtoResponseRu;
import com.example.site_pl_99.entity.EmployeeEntity;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeMapper {
    public static EmployeeDtoResponseRu mapEntityToDtoResponse(EmployeeEntity employeeEntity) {
       EmployeeDtoResponseRu dto = new EmployeeDtoResponseRu();
       dto.setFullName(employeeEntity.getFullName());
       dto.setBirthDate(employeeEntity.getDateBerth());
       dto.setDepartment(employeeEntity.getDepartmentRu());
       dto.setActive(employeeEntity.getActive());
       dto.setDateEmployment(employeeEntity.getDateEmployment());
       dto.setDateDismissal(employeeEntity.getDateDismissal());
       return dto;
    }
    public static EmployeeDtoResponseKg mapEntityToDtoResponseKg(EmployeeEntity employee) {
        EmployeeDtoResponseKg dtoKg = new EmployeeDtoResponseKg();
        dtoKg.setFullName(employee.getFullName());
        dtoKg.setBirthDate(employee.getDateBerth());
        dtoKg.setDepartment(employee.getDepartmentKg());
        dtoKg.setActive(employee.getActive());
        dtoKg.setDateEmployment(employee.getDateEmployment());
        dtoKg.setDateDismissal(employee.getDateDismissal());
        return dtoKg;
    }

    public static EmployeeEntity mapDtoToEntity(EmployeeDtoRequest entity) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setFullName(entity.getFullName());
        employeeEntity.setDateBerth(entity.getBirthDate());
        employeeEntity.setDepartmentKg(entity.getDepartment());
        employeeEntity.setDepartmentRu(entity.getDepartment());
        return employeeEntity;
    }
}
