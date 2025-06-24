package com.example.site_pl_99.mapper;


import com.example.site_pl_99.dto.EmployeeDtoRequest;
import com.example.site_pl_99.dto.EmployeeDtoRequestUpdate;
import com.example.site_pl_99.dto.EmployeeDtoResponse;
import com.example.site_pl_99.entity.EmployeeEntity;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public EmployeeDtoResponse mapEntityToDtoResponse(EmployeeEntity entity) {
        EmployeeDtoResponse dtoResponse = new EmployeeDtoResponse();
        dtoResponse.setId(entity.getId());
        dtoResponse.setDateBerth(entity.getDateBerth());
        dtoResponse.setFullName(entity.getFullName());
        dtoResponse.setImage(ImageMapper.mapEntityToDto(entity.getImage()));
        dtoResponse.setDepartment(
                LocaleContextHolder.getLocale().getLanguage().equalsIgnoreCase("ru")?
                        entity.getDepartmentRu() :
                        entity.getDepartmentKg()
        );
        dtoResponse.setDateEmployment(entity.getDateEmployment());
        dtoResponse.setDateDismissal(entity.getDateDismissal());
        return dtoResponse;
    }

    public EmployeeEntity mapDtoToEntity(EmployeeDtoRequest dtoRequest) {
        return new EmployeeEntity()
                .setFullName(dtoRequest.getFullName())
                .setDateBerth(dtoRequest.getDateBerth())
                .setImage(ImageMapper.mapDtoToEntity(dtoRequest.getImage()))
                .setDepartmentRu(dtoRequest.getDepartmentRu())
                .setDepartmentKg(dtoRequest.getDepartmentKg());
    }

    public EmployeeEntity mapDtoToEntityUpdate(EmployeeDtoRequestUpdate dtoRequest){
        EmployeeEntity entity = new EmployeeEntity();
        entity.setId(dtoRequest.getId());
        entity.setFullName(dtoRequest.getFullName());
        entity.setDateBerth(dtoRequest.getDateBerth());
        entity.setDateDismissal(dtoRequest.getDateDismissal());
        entity.setImage(ImageMapper.mapDtoToEntity(dtoRequest.getImage()));
        entity.setDepartmentRu(dtoRequest.getDepartmentRu());
        entity.setDepartmentKg(dtoRequest.getDepartmentKg());
        entity.setDateEmployment(dtoRequest.getDateEmployment());
        return entity;
    }
}
