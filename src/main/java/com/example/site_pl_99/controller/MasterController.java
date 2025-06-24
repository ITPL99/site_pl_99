package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.MasterDtoRequest;
import com.example.site_pl_99.dto.MasterDtoRequestUpdate;
import com.example.site_pl_99.dto.MasterDtoResponse;
import com.example.site_pl_99.enums.Active;
import com.example.site_pl_99.mapper.MasterMapper;
import com.example.site_pl_99.service.impl.MasterServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/master")
@SecurityRequirement(name = "bearerAuth")
public class MasterController {
    private final MasterServiceImpl masterService;

    public MasterController(MasterServiceImpl masterService) {
        this.masterService = masterService;
    }

    @GetMapping("/get-by-full-name")
    public ResponseEntity<MasterDtoResponse> getMasterByFullName(@RequestParam String fullName) {
        return ResponseEntity.ok(MasterMapper.toDto(masterService.getFullName(fullName)));
    }

    @GetMapping("/get-search-by-name")
    public ResponseEntity<List<MasterDtoResponse>> getMasterByName(@RequestParam String name) {
        return ResponseEntity.ok(masterService.searchByName(name).stream()
                .map(MasterMapper::toDto)
                .toList());
    }

    @GetMapping("/get-by-active-status")
    public ResponseEntity<List<MasterDtoResponse>> getMasterByActiveStatus(@RequestParam Active status) {
        return ResponseEntity.ok(masterService.getAllActiveStatus(status).stream()
                .map(MasterMapper::toDto)
                .toList());
    }

    @GetMapping("/get-all-by-date-berth")
    public ResponseEntity<List<MasterDtoResponse>> getMasterByDateBerth(@RequestParam LocalDate dateBerth) {
        return ResponseEntity.ok(masterService.getByDateBerth(dateBerth).stream()
                .map(MasterMapper::toDto)
                .toList());
    }

    @GetMapping("/get-all-by-profession")
    public ResponseEntity<List<MasterDtoResponse>> getMasterByProfession(@RequestParam String profession) {
        return ResponseEntity.ok(masterService.getByProfession(profession).stream()
                .map(MasterMapper::toDto)
                .toList());
    }

    @GetMapping("/get-all-by-date-employment")
    public ResponseEntity<List<MasterDtoResponse>> getMasterByDateEmployment(@RequestParam LocalDate dateEmployment) {
        return ResponseEntity.ok(masterService.getByDateEmployment(dateEmployment).stream()
                .map(MasterMapper::toDto)
                .toList());
    }

    @GetMapping("/get-all-by-date-dismissal")
    public ResponseEntity<List<MasterDtoResponse>> getMasterByDateDismissal(@RequestParam LocalDate dateDismissal) {
        return ResponseEntity.ok(masterService.getByDateDismissal(dateDismissal).stream().map(MasterMapper::toDto).toList());
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<MasterDtoResponse> getMasterById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(MasterMapper.toDto(masterService.getById(id)));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<MasterDtoResponse>> getAll() {
        return ResponseEntity.ok(masterService.getAll().stream()
                .map(MasterMapper::toDto)
                .toList());
    }

    @PostMapping("/save")
    public ResponseEntity<MasterDtoResponse> save(@RequestBody MasterDtoRequest entity) {
        return ResponseEntity.ok(MasterMapper.toDto(masterService.save(MasterMapper.toEntity(entity))));
    }

    @PutMapping("/update-master")
    public ResponseEntity<MasterDtoResponse> updateTeacher(@RequestBody MasterDtoRequestUpdate entity){
        return ResponseEntity.ok(MasterMapper.toDto(masterService.update(MasterMapper.toEntityUpdate(entity))));
    }

    @DeleteMapping("/delete-by-id/{id}")
    public void delete(@PathVariable("id") Long id) {
        masterService.deleteById(id);
    }
}
