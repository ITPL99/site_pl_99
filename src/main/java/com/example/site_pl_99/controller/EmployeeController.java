package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.EmployeeDtoRequest;
import com.example.site_pl_99.dto.EmployeeDtoResponse;
import com.example.site_pl_99.enums.Active;
import com.example.site_pl_99.mapper.EmployeeMapper;
import com.example.site_pl_99.service.EmployeeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
@SecurityRequirement(name = "bearerAuth")
public class EmployeeController  {

    private final EmployeeService employeeService;
   private final EmployeeMapper employeeMapper;

    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @PostMapping("/get-by-date-name")
    public ResponseEntity<EmployeeDtoResponse> getFullName(@RequestParam String fullName) {
        return ResponseEntity.ok(employeeMapper.mapEntityToDtoResponse(employeeService.getFullName(fullName)));
    }

    @PostMapping("/get-by-date-containing-name")
    public ResponseEntity<List<EmployeeDtoResponse>> getAllEmployeesContentName(@RequestParam String fullName) {
        return ResponseEntity.ok(employeeService.getAllEmployeesContentName(fullName).stream().map(employeeMapper::mapEntityToDtoResponse).toList());
    }

    @PostMapping("/get-by-date-date-berth")
    public ResponseEntity<List<EmployeeDtoResponse>> getAllEmployeesByDateBerth(@RequestParam LocalDate dateBerth) {
        return ResponseEntity.ok(employeeService.getAllEmployeesByDateBerth(dateBerth).stream().map(employeeMapper::mapEntityToDtoResponse).toList());
    }

    @PostMapping("/get-by-date-status")
    public ResponseEntity<List<EmployeeDtoResponse>> getAllEmployeesByStatusActive(@RequestParam Active status) {
        return ResponseEntity.ok(employeeService.getAllEmployeesByStatusActive(status).stream().map(employeeMapper::mapEntityToDtoResponse).toList());
    }

    @PostMapping("/get-by-date-department")
    public ResponseEntity<List<EmployeeDtoResponse>> getAllEmployeesByDepartment(@RequestParam String department) {
        return ResponseEntity.ok(employeeService.getAllEmployeesByDepartment(department).stream().map(employeeMapper::mapEntityToDtoResponse).toList());
    }

    @PostMapping("/get-by-date-employment")
    public ResponseEntity<List<EmployeeDtoResponse>> getAllEmployeesByDateEmployment(@RequestParam LocalDate dateEmployment) {
        return ResponseEntity.ok(employeeService.getAllEmployeesByDateEmployment(dateEmployment).stream().map(employeeMapper::mapEntityToDtoResponse).toList());
    }

    @PostMapping("/get-by-date-dismissal")
    public ResponseEntity<List<EmployeeDtoResponse>> getAllEmployeesByDateDismissal(@RequestParam LocalDate dateDismissal) {
        return ResponseEntity.ok(employeeService.getAllEmployeesByDateDismissal(dateDismissal).stream().map(employeeMapper::mapEntityToDtoResponse).toList());
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<EmployeeDtoResponse>> getFullAll() {
        return ResponseEntity.ok(employeeService.getFullAll().stream().map(employeeMapper::mapEntityToDtoResponse).toList());
    }

    @PostMapping("/get-by-id/{id}")
    public ResponseEntity<EmployeeDtoResponse> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(employeeMapper.mapEntityToDtoResponse(employeeService.getById(id)));
    }

    @PostMapping("/create")
    public ResponseEntity<EmployeeDtoResponse> save(@RequestBody EmployeeDtoRequest dtoRequest) {
        return ResponseEntity.ok(employeeMapper.mapEntityToDtoResponse(employeeService.save(employeeMapper.mapDtoToEntity(dtoRequest))));
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDtoResponse>> getAll() {
        return ResponseEntity.ok(employeeService.getAll().stream().map(employeeMapper::mapEntityToDtoResponse).toList());
    }

    @DeleteMapping("/delete-by-id/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        employeeService.deleteById(id);
    }
}
