package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.entity.TeacherEntity;
import com.example.site_pl_99.enums.Active;
import com.example.site_pl_99.repository.TeacherRepository;
import com.example.site_pl_99.service.TeacherService;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDate;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }


    @Override
    public TeacherEntity getById(Long id) {
        return teacherRepository.findById(id)
                .filter(teacherEntity -> teacherEntity.getActive().equals(Active.DELETED))
                .orElseThrow(() -> new NotFoundException("Teacher not found"));
    }

    @Override
    public TeacherEntity save(TeacherEntity entity) {
        if (entity.getFullName() == null || entity.getFullName().isBlank()) {
            throw new RuntimeException("Поле fullName не может быть пустым");
        }
        if (entity.getLinkPortfolio() == null) {
            throw new RuntimeException("Должна быть указана хотя бы одна портфолио");
        }
        if (entity.getDateBerth() == null) {
            throw new RuntimeException("Дата рождения обязательна");
        }

        return teacherRepository.save(entity);
    }

    @Override
    public List<TeacherEntity> getAll() {
        return teacherRepository.findAll().stream()
                .filter(teacherEntity -> !teacherEntity.getActive().equals(Active.DELETED))
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        TeacherEntity teacherEntity = getById(id);
        teacherEntity.setActive(Active.DELETED);
        teacherEntity.setDateDismissal(LocalDate.now());
        teacherRepository.save(teacherEntity);
    }

    @Override
    public TeacherEntity getFullName(String fullName) {
        return teacherRepository.findByFullName(fullName)
                .filter(master -> !master.getActive().equals(Active.DELETED))
                .orElseThrow(() -> new NotFoundException("Teacher not found"));
    }

    @Override
    public List<TeacherEntity> searchByName(String namePart) {
        return teacherRepository.findByFullNameContaining(namePart)
                .orElseThrow(() -> new NotFoundException("Teacher not found")).stream()
                .filter(teacherEntity -> !teacherEntity.getActive().equals(Active.DELETED))
                .toList();
    }

    @Override
    public List<TeacherEntity> getByDateBerth(LocalDate dateBerth) {
        return teacherRepository.findAllByDateBerth(dateBerth)
                .orElseThrow(() -> new NotFoundException("Teacher not found")).stream()
                .filter(teacherEntity -> !teacherEntity.getActive().equals(Active.DELETED))
                .toList();
    }

    @Override
    public List<TeacherEntity> getByStatusActive(Active status) {
        return teacherRepository.findAllByActive(status)
                .orElseThrow(() -> new NotFoundException("Teacher not found"));
    }

    @Override
    public List<TeacherEntity> getByPortfolio(String department) {
        return teacherRepository.findAllByLinkPortfolio(department)
                .orElseThrow(() -> new NotFoundException("Teacher not found")).stream()
                .filter(teacherEntity -> !teacherEntity.getActive().equals(Active.DELETED))
                .toList();
    }

    @Override
    public List<TeacherEntity> getByDateEmployment(LocalDate dateEmployment) {
        return teacherRepository.findAllByDateEmployment(dateEmployment)
                .orElseThrow(() -> new NotFoundException("Teacher not found")).stream()
                .filter(teacherEntity -> !teacherEntity.getActive().equals(Active.DELETED))
                .toList();
    }

    @Override
    public List<TeacherEntity> getDateDismissal(LocalDate dateDismissal) {
        return teacherRepository.findAllByDateDismissal(dateDismissal)
                .orElseThrow(() -> new NotFoundException("Teacher not found")).stream()
                .filter(teacherEntity -> !teacherEntity.getActive().equals(Active.DELETED))
                .toList();
    }

    @Override
    public TeacherEntity update(TeacherEntity entity) {
        TeacherEntity existing = teacherRepository.findById(entity.getId())
                .orElseThrow(() -> new NotFoundException("Учитель не найден"));

        if (entity.getActive().equals(Active.DELETED)) {
            throw new RuntimeException("Нельзя обновить удалённого мастера");
        }

        existing.setFullName(entity.getFullName());
        existing.setLinkPortfolio(entity.getLinkPortfolio());
        existing.setDateBerth(entity.getDateBerth());
        existing.setDateDismissal(entity.getDateDismissal());
        existing.setImage(entity.getImage());
        existing.setDateEmployment(entity.getDateEmployment());
        existing.setActive(Active.UPDATED);

        return teacherRepository.save(existing);
    }
}
