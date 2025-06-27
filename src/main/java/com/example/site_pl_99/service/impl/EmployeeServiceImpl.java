package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.entity.EmployeeEntity;
import com.example.site_pl_99.enums.Active;
import com.example.site_pl_99.excaption.EmployeeNotFoundException;
import com.example.site_pl_99.excaption.UniquenessViolationException;
import com.example.site_pl_99.excaption.ValidationError;
import com.example.site_pl_99.repository.EmployeeRepository;
import com.example.site_pl_99.service.EmployeeService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeEntity getFullName(String fullName) {
        return employeeRepository.findByFullName(fullName)
                .orElseThrow(() -> new EmployeeNotFoundException("error.findEmplByFullName"));
    }

    @Override
    public List<EmployeeEntity> searchByName(String namePart) {
        return employeeRepository.findByFullNameContainingIgnoreCase(namePart);
    }

    @Override
    public List<EmployeeEntity> getByDateBerth(LocalDate dateBerth) {
        return employeeRepository.findByDateBerth(dateBerth);
    }

    @Override
    public List<EmployeeEntity> getByStatusActive(Active status) {
        return employeeRepository.findByActive(status);
    }

    @Override
    public List<EmployeeEntity> getByDepartment(String department) {
        return employeeRepository.findByDepartmentRuIgnoreCaseOrDepartmentKgIgnoreCase(department, department);
    }

    @Override
    public List<EmployeeEntity> getByDateEmployment(LocalDate dateEmployment) {
        return employeeRepository.findByDateEmployment(dateEmployment);
    }

    @Override
    public List<EmployeeEntity> getByDateDismissal(LocalDate dateDismissal) {
        return employeeRepository.findByDateDismissal(dateDismissal);
    }

    @Override
    public EmployeeEntity update(EmployeeEntity employeeEntity) {
        EmployeeEntity existing = employeeRepository.findById(employeeEntity.getId())
                .orElseThrow(() -> new EmployeeNotFoundException("error.findEmplById"));

        existing.setFullName(employeeEntity.getFullName())
                .setDateBerth(employeeEntity.getDateBerth())
                .setImage(employeeEntity.getImage())
                .setDepartmentRu(employeeEntity.getDepartmentRu())
                .setDepartmentKg(employeeEntity.getDepartmentKg())
                .setDateEmployment(employeeEntity.getDateEmployment())
                .setDateDismissal(employeeEntity.getDateDismissal())
                .setActive(Active.UPDATED);
        return employeeRepository.save(existing);
    }

    @Override
    public EmployeeEntity getById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("error.findEmplById"));
    }

    @Override
    public EmployeeEntity save(EmployeeEntity entity) {
        try {
            if (entity.getFullName() == null || entity.getFullName().isBlank()) {
                throw new ValidationError("error.isEmptyFullName");
            }
            if (entity.getDateEmployment() == null) {
                throw new ValidationError("error.isEmptyDateEmployment");
            }
            if (entity.getDateBerth() == null) {
                throw new ValidationError("error.isEmptyDateBerth");
            }
            return employeeRepository.save(entity);
        }catch (DataIntegrityViolationException e){
            throw new UniquenessViolationException(e.getMessage());
        }
    }

    @Override
    public List<EmployeeEntity> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        EmployeeEntity entity = getById(id);
        entity.setActive(Active.DELETED);
        entity.setDateDismissal(LocalDate.now());
        employeeRepository.save(entity);
    }
}
