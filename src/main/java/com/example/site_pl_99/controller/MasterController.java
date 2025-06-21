package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.MasterDtoRequest;
import com.example.site_pl_99.dto.MasterDtoResponse;
import com.example.site_pl_99.entity.MasterEntity;
import com.example.site_pl_99.mapper.MasterMapper;
import com.example.site_pl_99.service.impl.MasterServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/master")
public class MasterController {
    private final MasterServiceImpl masterService;

    public MasterController(MasterServiceImpl masterService) {
        this.masterService = masterService;
    }

    @GetMapping("/get-by-full-name")
    public ResponseEntity<?> getMasterByFullName(@RequestParam String fullName) {
        return ResponseEntity.ok(MasterMapper.toDto(masterService.getFullName(fullName)));
    }

    @GetMapping("/get-all-by-content-name")
    public ResponseEntity<?> getMasterByContentName(@RequestParam String contentName) {
        return ResponseEntity.ok(masterService.getAllMastersContentName(contentName).stream().map(MasterMapper::toDto).toList());
    }

    @GetMapping("/get-all-by-date-berth")
    public ResponseEntity<?> getMasterByDateBerth(@RequestParam LocalDate dateBerth) {
        return ResponseEntity.ok(masterService.getAllMastersByDateBerth(dateBerth).stream().map(MasterMapper::toDto).toList());
    }

    @GetMapping("/get-all-by-profession")
    public ResponseEntity<?> getMasterByProfession(@RequestParam String profession) {
        return ResponseEntity.ok(masterService.getAllMastersByProfession(profession).stream().map(MasterMapper::toDto).toList());
    }

    @GetMapping("/get-all-by-date-employment")
    public ResponseEntity<?> getMasterByDateEmployment(@RequestParam LocalDate dateEmployment) {
        return ResponseEntity.ok(masterService.getAllMastersByDateEmployment(dateEmployment).stream().map(MasterMapper::toDto).toList());
    }

    @GetMapping("/get-all-by-date-dismissal")
    public ResponseEntity<?> getMasterByDateDismissal(@RequestParam LocalDate dateDismissal) {
        return ResponseEntity.ok(masterService.getAllMastersByDateDismissal(dateDismissal).stream().map(MasterMapper::toDto).toList());
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<?> getMasterById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(MasterMapper.toDto(masterService.getById(id)));
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(masterService.getAll().stream().map(MasterMapper::toDto).toList());
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody MasterDtoRequest entity) {
        return ResponseEntity.ok(MasterMapper.toDto(masterService.save(MasterMapper.toEntity(entity))));
    }

    @DeleteMapping("/delete-by-id/{id}")
    public void delete(@PathVariable("id") Long id) {
        masterService.deleteById(id);
    }
}
