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
                .filter(master -> !master.getActive().equals(Active.DELETED))
                .orElseThrow(() -> new NotFoundException("Мастер не найден"));
    }

    @Override
    public List<MasterEntity> searchByName(String namePart) {
        return masterRepository.findAllByFullNameContaining(namePart)
                .stream()
                .filter(master -> !master.getActive().equals(Active.DELETED))
                .toList();
    }

    @Override
    public List<MasterEntity> getAllActiveStatus(Active status) {
        return masterRepository.findAllByActive(status);
    }

    @Override
    public List<MasterEntity> getByDateBerth(LocalDate dateBerth) {
        return masterRepository.findAllByDateBerth(dateBerth).stream()
                .filter(master -> !master.getActive().equals(Active.DELETED))
                .toList();
    }

    @Override
    public List<MasterEntity> getByProfession(String department) {
        return masterRepository.findAllByProfessionRuOrProfessionKg(department, department)
                .stream()
                .filter(master -> !master.getActive().equals(Active.DELETED))
                .toList();
    }

    @Override
    public List<MasterEntity> getByDateEmployment(LocalDate dateEmployment) {
        return masterRepository.findAllByDateEmployment(dateEmployment)
                .stream()
                .filter(master -> !master.getActive().equals(Active.DELETED))
                .toList();
    }

    @Override
    public List<MasterEntity> getByDateDismissal(LocalDate dateDismissal) {
        return masterRepository.findAllByDateDismissal(dateDismissal)
                .stream()
                .filter(master -> !master.getActive().equals(Active.DELETED))
                .toList();
    }

    @Override
    public MasterEntity update(MasterEntity entity) {
        MasterEntity existing = masterRepository.findById(entity.getId())
                .orElseThrow(() -> new NotFoundException("Мастер не найден"));

        if (entity.getActive().equals(Active.DELETED)) {
            throw new RuntimeException("Нельзя обновить удалённого мастера");
        }

        existing.setFullName(entity.getFullName());
        existing.setProfessionKg(entity.getProfessionKg());
        existing.setProfessionRu(entity.getProfessionRu());
        existing.setDateBerth(entity.getDateBerth());
        existing.setDateDismissal(entity.getDateDismissal());
        existing.setImage(entity.getImage());
        existing.setDateEmployment(entity.getDateEmployment());
        existing.setActive(Active.UPDATED);

        return masterRepository.save(existing);
    }


    @Override
    public MasterEntity getById(Long id) {
        return masterRepository.findById(id)
                .filter(master -> !master.getActive().equals(Active.DELETED))
                .orElseThrow(() -> new NotFoundException("Мастер не найден"));
    }

    @Override
    public MasterEntity save(MasterEntity entity) {
        if (entity.getFullName() == null || entity.getFullName().isBlank()) {
            throw new RuntimeException("Поле fullName не может быть пустым");
        }
        if (entity.getProfessionKg() == null && entity.getProfessionRu() == null) {
            throw new RuntimeException("Должна быть указана хотя бы одна профессия");
        }
        if (entity.getDateBerth() == null) {
            throw new RuntimeException("Дата рождения обязательна");
        }

        return masterRepository.save(entity);
    }

    @Override
    public List<MasterEntity> getAll() {
        return masterRepository.findAll()
                .stream()
                .filter(master -> !master.getActive().equals(Active.DELETED))
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        MasterEntity master = getById(id);
        master.setActive(Active.DELETED);
        master.setDateDismissal(LocalDate.now());
        masterRepository.save(master);
    }
}
