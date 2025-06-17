package com.example.site_pl_99.mapper;

import com.example.site_pl_99.dto.EmployeeDtoRequest;
import com.example.site_pl_99.dto.EmployeeDtoResponse;
import com.example.site_pl_99.entity.EmployeeEntity;

public class EmployeeMapper {
    public static EmployeeEntity toEntity(EmployeeDtoRequest employeeDtoRequest) {
        return new EmployeeEntity().setFullName(employeeDtoRequest.getFullName())
                .setBirthDate(employeeDtoRequest.getBirthDate())
                .setDepartment(employeeDtoRequest.getDepartment());
    }
    public static EmployeeDtoResponse toResponse(EmployeeEntity employeeEntity) {
        return new EmployeeDtoResponse().setId(employeeEntity.getId())
                .setFullName(employeeEntity.getFullName())
                .setBirthDate(employeeEntity.getBirthDate())
                .setDepartment(employeeEntity.getDepartment())
                .setActive(employeeEntity.getActive())
                .setDateStarted(employeeEntity.getDateStarted())
                .setDateFired(employeeEntity.getDateFired())
                .setImageId(employeeEntity.getId());
    }
}
