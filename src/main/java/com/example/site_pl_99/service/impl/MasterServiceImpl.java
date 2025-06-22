package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.entity.MasterEntity;
import com.example.site_pl_99.enums.Active;
import com.example.site_pl_99.repository.MasterRepository;
import com.example.site_pl_99.service.MasterService;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDate;
import java.util.List;
@Service
public class MasterServiceImpl implements MasterService {
    private final MasterRepository masterRepository;

    public MasterServiceImpl(MasterRepository masterRepository) {
        this.masterRepository = masterRepository;
    }

    @Override
    public MasterEntity getFullName(String fullName) {
        return masterRepository.findByFullName(fullName).orElseThrow(() -> new NotFoundException("Не найден"));
    }

    @Override
    public List<MasterEntity> getAllMastersContentName(String fullName) {
        return masterRepository.findAllByFullNameContaining(fullName).orElseThrow(() -> new NotFoundException("Не найден"));
    }

    @Override
    public List<MasterEntity> getAllMastersByDateBerth(LocalDate dateBerth) {
        return masterRepository.findAllByDateBerth(dateBerth).orElseThrow(() -> new NotFoundException("Не найден"));
    }

    @Override
    public List<MasterEntity> getAllMastersByStatusActive(Active status) {
        return masterRepository.findAllByActive(status).orElseThrow(() -> new NotFoundException("Не найден"));
    }

    @Override
    public List<MasterEntity> getAllMastersByProfession(String department) {
        return masterRepository.findAllByProfessionRuOrProfessionKg(department,department).orElseThrow(() -> new NotFoundException("Не найден"));
    }

    @Override
    public List<MasterEntity> getAllMastersByDateEmployment(LocalDate dateEmployment) {
        return masterRepository.findAllByDateEmployment(dateEmployment).orElseThrow(() -> new NotFoundException("Не найден"));
    }

    @Override
    public List<MasterEntity> getAllMastersByDateDismissal(LocalDate dateDismissal) {
        return masterRepository.findAllByDateDismissal(dateDismissal).orElseThrow(() -> new NotFoundException("Не найден"));
    }

    @Override
    public MasterEntity getById(Long id) {
        return masterRepository.findById(id).orElseThrow(() -> new NotFoundException("Не найден"));
    }

    @Override
    public MasterEntity save(MasterEntity entity) {
        return masterRepository.save(entity);
    }

    @Override
    public List<MasterEntity> getAll() {
        return masterRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        MasterEntity masterEntity = getById(id);
        masterEntity.setActive(Active.DELETED);
        masterRepository.save(masterEntity);
    }
}
