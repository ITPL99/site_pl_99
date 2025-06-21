package com.example.site_pl_99.mapper;


import com.example.site_pl_99.dto.EmployeeDtoRequest;
import com.example.site_pl_99.dto.EmployeeDtoResponse;
import com.example.site_pl_99.entity.EmployeeEntity;
import com.example.site_pl_99.utils.Internalization;
import org.springframework.cglib.core.Local;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {


    public EmployeeDtoResponse mapEntityToDtoResponse(EmployeeEntity entity) {
        EmployeeDtoResponse dtoResponse = new EmployeeDtoResponse();
        dtoResponse.setId(entity.getId());
        dtoResponse.setDateBerth(entity.getDateBerth());
        dtoResponse.setFullName(entity.getFullName());
        dtoResponse.setImageFileName(entity.getImage().getFileName());
        dtoResponse.setDepartment(
                LocaleContextHolder.getLocale().getLanguage().equalsIgnoreCase("ru")?
                        entity.getDepartmentRu() :
                        entity.getDepartmentKg()
        );

        return dtoResponse;
    }

    public EmployeeEntity mapDtoToEntity(EmployeeDtoRequest dtoRequest) {
        return new EmployeeEntity()
                .setFullName(dtoRequest.getFullName())
                .setDateBerth(dtoRequest.getDateBerth())
                .setImage(ImageMapper.mapDtoToEntity(dtoRequest.getImage()));
    }
}
