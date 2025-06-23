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
        return masterRepository.findByFullName(fullName)
                .filter(master -> master.getActive() != Active.DELETED)
                .orElseThrow(() -> new NotFoundException("Мастер не найден"));
    }

    @Override
    public List<MasterEntity> searchByFullName(String namePart) {
        return masterRepository.findAllByFullNameContaining(namePart)
                .orElseThrow(() -> new NotFoundException("Мастера не найдены"))
                .stream()
                .filter(master -> master.getActive() != Active.DELETED)
                .toList();
    }

    @Override
    public List<MasterEntity> getAllActiveStatus(Active status) {
        return masterRepository.findAllByActive(status)
                .orElseThrow(() -> new NotFoundException("Мастера не найдены"));
    }

    @Override
    public List<MasterEntity> getByDateBerth(LocalDate dateBerth) {
        return masterRepository.findAllByDateBerth(dateBerth)
                .orElseThrow(() -> new NotFoundException("Мастера не найдены"))
                .stream()
                .filter(master -> master.getActive() != Active.DELETED)
                .toList();
    }

    @Override
    public List<MasterEntity> getByProfession(String department) {
        return masterRepository.findAllByProfessionRuOrProfessionKg(department, department)
                .orElseThrow(() -> new NotFoundException("Мастера не найдены"))
                .stream()
                .filter(master -> master.getActive() != Active.DELETED)
                .toList();
    }

    @Override
    public List<MasterEntity> getByDateEmployment(LocalDate dateEmployment) {
        return masterRepository.findAllByDateEmployment(dateEmployment)
                .orElseThrow(() -> new NotFoundException("Мастера не найдены"))
                .stream()
                .filter(master -> master.getActive() != Active.DELETED)
                .toList();
    }

    @Override
    public List<MasterEntity> getByDateDismissal(LocalDate dateDismissal) {
        return masterRepository.findAllByDateDismissal(dateDismissal)
                .orElseThrow(() -> new NotFoundException("Мастера не найдены"))
                .stream()
                .filter(master -> master.getActive() != Active.DELETED)
                .toList();
    }


    @Override
    public MasterEntity getById(Long id) {
        return masterRepository.findById(id)
                .filter(master -> master.getActive() != Active.DELETED)
                .orElseThrow(() -> new NotFoundException("Мастер не найден"));
    }

    @Override
    public MasterEntity save(MasterEntity entity) {
        return masterRepository.save(entity);
    }

    @Override
    public List<MasterEntity> getAll() {
        return masterRepository.findAll()
                .stream()
                .filter(master -> master.getActive() != Active.DELETED)
                .toList();
    }

    @Override
    public void deleteById(Long id) {

    }
}
