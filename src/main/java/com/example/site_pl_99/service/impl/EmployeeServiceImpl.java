package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.entity.EmployeeEntity;
import com.example.site_pl_99.enums.Active;
import com.example.site_pl_99.excaption.NotFoundException;
import com.example.site_pl_99.repository.EmployeeRepository;
import com.example.site_pl_99.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;


    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public EmployeeEntity getFullName(String fullName) {
        return employeeRepository.findByFullName(fullName).orElseThrow(() ->  new NotFoundException("Not Found"));
    }

    @Override
    public List<EmployeeEntity> getAllEmployeesContentName(String fullName) {
        return employeeRepository.findByFullNameContaining(fullName).orElseThrow(() ->  new NotFoundException("Not Found"));
    }

    @Override
    public List<EmployeeEntity> getAllEmployeesByDateBerth(LocalDate dateBerth) {
        return employeeRepository.findAllByDateBerth(dateBerth).orElseThrow(() ->  new NotFoundException("Not Found"));
    }

    @Override
    public List<EmployeeEntity> getAllEmployeesByStatusActive(Active status) {
        return employeeRepository.findAllByActive(status).orElseThrow(() ->  new NotFoundException("Not Found"));
    }

    @Override
    public List<EmployeeEntity> getAllEmployeesByDepartment(String department) {
        return employeeRepository.findAllByDepartmentKgOrDepartmentRu(department, department).orElseThrow(() ->  new NotFoundException("Not Found"));
    }

    @Override
    public List<EmployeeEntity> getAllEmployeesByDateEmployment(LocalDate dateEmployment) {
       return employeeRepository.findAllByDateEmployment(dateEmployment).orElseThrow(() ->  new NotFoundException("Not Found"));
    }

    @Override
    public List<EmployeeEntity> getAllEmployeesByDateDismissal(LocalDate dateDismissal) {
        return employeeRepository.findAllByDateDismissal(dateDismissal).orElseThrow(()->new NotFoundException("Not Found"));
    }

    @Override
    public EmployeeEntity getById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() ->  new NotFoundException("Not Found"));
    }

    @Override
    public EmployeeEntity save(EmployeeEntity entity) {
        return employeeRepository.save(entity);
    }

    @Override
    public List<EmployeeEntity> getAll() {
        return employeeRepository.findAll().stream().filter(e -> e.getActive() == Active.ACTIVE).collect(Collectors.toList());
    }

    @Override
    public List<EmployeeEntity> getFullAll() {
        return employeeRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        EmployeeEntity employeeEntity = getById(id);
        employeeEntity.setDateDismissal(LocalDate.now());
        employeeEntity.setActive(Active.DELETED);
        employeeRepository.save(employeeEntity);
    }
}
