package com.example.site_pl_99.mapper;

import com.example.site_pl_99.dto.EmployeeDtoCreate;
import com.example.site_pl_99.dto.EmployeeDtoResponse;
import com.example.site_pl_99.entity.EmployeeEntity;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeMapper {
    public static EmployeeDtoResponse mapEntityToDtoResponse(EmployeeEntity fullName) {
        return new  EmployeeDtoResponse();
    }

    public static List<EmployeeDtoResponse> mapAllEntityToDtoResponse(List<EmployeeEntity> allEmployeesContentName) {
        return allEmployeesContentName.stream().map(EmployeeMapper::mapEntityToDtoResponse).collect(Collectors.toList());
    }

    public static EmployeeEntity mapDtoToEntity(EmployeeDtoCreate entity) {
        return new   EmployeeEntity();
    }
}
