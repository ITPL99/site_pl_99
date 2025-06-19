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
    public ResponseEntity<EmployeeDtoResponseRu> getFullName(@RequestParam String fullName) {
        return ResponseEntity.ok(
                EmployeeMapper.mapEntityToDtoResponse(employeeService.getFullName(fullName))
        );
    }

    @PostMapping("/get-by-date-containing-name")
    public ResponseEntity<List<EmployeeDtoResponseRu>> getAllEmployeesContentName(@RequestParam String fullName) {
        return ResponseEntity.ok(
                EmployeeMapper.mapAllEntityToDtoResponse(employeeService.getAllEmployeesContentName(fullName))
        );
    }

    @PostMapping("/get-by-date-date-berth")
    public ResponseEntity<List<EmployeeDtoResponseRu>> getAllEmployeesByDateBerth(@RequestParam LocalDate dateBerth) {
        return ResponseEntity.ok(
                EmployeeMapper.mapAllEntityToDtoResponse(employeeService.getAllEmployeesByDateBerth(dateBerth))
        );
    }

    @PostMapping("/get-by-date-status")
    public ResponseEntity<List<EmployeeDtoResponseRu>> getAllEmployeesByStatusActive(@RequestParam Active status) {
        return ResponseEntity.ok(
                EmployeeMapper.mapAllEntityToDtoResponse(employeeService.getAllEmployeesByStatusActive(status))
        );
    }

    @PostMapping("/get-by-date-department")
    public ResponseEntity<List<EmployeeDtoResponseRu>> getAllEmployeesByDepartment(@RequestParam String department) {
        return ResponseEntity.ok(
                EmployeeMapper.mapAllEntityToDtoResponse(employeeService.getAllEmployeesByDepartment(department))
        );
    }

    @PostMapping("/get-by-date-employment")
    public ResponseEntity<List<EmployeeDtoResponseRu>> getAllEmployeesByDateEmployment(@RequestParam LocalDate dateEmployment) {
        return ResponseEntity.ok(
                EmployeeMapper.mapAllEntityToDtoResponse(employeeService.getAllEmployeesByDateEmployment(dateEmployment))
        );
    }

    @PostMapping("/get-by-date-dismissal")
    public ResponseEntity<List<EmployeeDtoResponseRu>> getAllEmployeesByDateDismissal(
            @RequestParam LocalDate dateDismissal) {
        return ResponseEntity.ok(
                EmployeeMapper.mapAllEntityToDtoResponse(employeeService.getAllEmployeesByDateDismissal(dateDismissal))
        );
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<EmployeeDtoResponseRu>> getFullAll() {
        return ResponseEntity.ok(
                EmployeeMapper.mapAllEntityToDtoResponse(employeeService.getFullAll())
        );
    }

    @PostMapping("/get-by-id")
    public ResponseEntity<EmployeeDtoResponseRu> getById(@RequestParam Long id) {
        return ResponseEntity.ok(
                EmployeeMapper.mapEntityToDtoResponse(employeeService.getById(id))
        );
    }

    @PostMapping("/create")
    public ResponseEntity<EmployeeDtoResponseRu> save(EmployeeDtoRequest entity) {
        return ResponseEntity.ok(
                EmployeeMapper.mapEntityToDtoResponse(employeeService.save(
                        EmployeeMapper.mapDtoToEntity( entity)
                ))
        );
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDtoResponseRu>> getAll() {
        return ResponseEntity.ok(
                EmployeeMapper.mapAllEntityToDtoResponse(employeeService.getAll())
        );
    }

    @DeleteMapping("/delete")
    public void deleteById( @RequestParam Long id) {
        employeeService.deleteById(id);
    }
}
