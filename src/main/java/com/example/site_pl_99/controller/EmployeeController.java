package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.EmployeeDtoRequest;
import com.example.site_pl_99.dto.EmployeeDtoResponseRu;
import com.example.site_pl_99.enums.Active;
import com.example.site_pl_99.mapper.EmployeeMapper;
import com.example.site_pl_99.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController  {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/get-by-date-name")
    public ResponseEntity<?> getFullName(@RequestParam String fullName) {
        return ResponseEntity.ok(EmployeeMapper.mapEntityToDtoResponse(employeeService.getFullName(fullName)));
    }

    @PostMapping("/get-by-date-containing-name")
    public ResponseEntity<List<?>> getAllEmployeesContentName(@RequestParam String fullName) {
        return ResponseEntity.ok(employeeService.getAllEmployeesContentName(fullName).stream().map(EmployeeMapper::mapEntityToDtoResponse).toList());
    }

    @PostMapping("/get-by-date-date-berth")
    public ResponseEntity<List<?>> getAllEmployeesByDateBerth(@RequestParam LocalDate dateBerth) {
        return ResponseEntity.ok(employeeService.getAllEmployeesByDateBerth(dateBerth).stream().map(EmployeeMapper::mapEntityToDtoResponse).toList());
    }

    @PostMapping("/get-by-date-status")
    public ResponseEntity<List<?>> getAllEmployeesByStatusActive(@RequestParam Active status) {
        return ResponseEntity.ok(employeeService.getAllEmployeesByStatusActive(status).stream().map(EmployeeMapper::mapEntityToDtoResponse).toList());
    }

    @PostMapping("/get-by-date-department")
    public ResponseEntity<List<?>> getAllEmployeesByDepartment(@RequestParam String department) {
        return ResponseEntity.ok(employeeService.getAllEmployeesByDepartment(department).stream().map(EmployeeMapper::mapEntityToDtoResponse).toList());
    }

    @PostMapping("/get-by-date-employment")
    public ResponseEntity<List<?>> getAllEmployeesByDateEmployment(@RequestParam LocalDate dateEmployment) {
        return ResponseEntity.ok(employeeService.getAllEmployeesByDateEmployment(dateEmployment).stream().map(EmployeeMapper::mapEntityToDtoResponse).toList());
    }

    @PostMapping("/get-by-date-dismissal")
    public ResponseEntity<List<?>> getAllEmployeesByDateDismissal(@RequestParam LocalDate dateDismissal) {
        return ResponseEntity.ok(employeeService.getAllEmployeesByDateDismissal(dateDismissal).stream().map(EmployeeMapper::mapEntityToDtoResponse).toList());
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<?>> getFullAll() {
        return ResponseEntity.ok(employeeService.getFullAll().stream().map(EmployeeMapper::mapEntityToDtoResponse).toList());
    }

    @PostMapping("/get-by-id/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(EmployeeMapper.mapEntityToDtoResponse(employeeService.getById(id)));
    }

    @PostMapping("/create")
    public ResponseEntity<?> save(EmployeeDtoRequest entity) {
        return ResponseEntity.ok(EmployeeMapper.mapEntityToDtoResponse(employeeService.save(EmployeeMapper.mapDtoToEntity(entity))));
    }

    @GetMapping("/all")
    public ResponseEntity<List<?>> getAll() {
        return ResponseEntity.ok(employeeService.getAll().stream().map(EmployeeMapper::mapEntityToDtoResponse).toList());
    }

    @DeleteMapping("/delete-by-id/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        employeeService.deleteById(id);
    }
}
