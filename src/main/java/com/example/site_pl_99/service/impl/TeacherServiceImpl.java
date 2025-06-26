package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.entity.TeacherEntity;
import com.example.site_pl_99.enums.Active;
import com.example.site_pl_99.excaption.UniquenessViolationException;
import com.example.site_pl_99.excaption.TeacherNotFoundException;
import com.example.site_pl_99.excaption.ValidationError;
import com.example.site_pl_99.repository.TeacherRepository;
import com.example.site_pl_99.service.TeacherService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

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
                .filter(teacherEntity -> teacherEntity.getActive().equals(Active.ACTIVE))
                .orElseThrow(() -> new TeacherNotFoundException("error.findTeacher"));
    }

    @Override
    public TeacherEntity save(TeacherEntity entity) {
        try {
            if (entity.getFullName() == null || entity.getFullName().isBlank()) {
                throw new ValidationError("error.isEmptyFullName");
            }
            if (entity.getLinkPortfolio() == null) {
                throw new ValidationError("error.isEmptyPortfolio");
            }
            if (entity.getDateBerth() == null) {
                throw new ValidationError("error.isEmptyDateBerth");
            }

            return teacherRepository.save(entity);
        }catch (DataIntegrityViolationException e){
            throw new UniquenessViolationException(e.getMessage());
        }
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
                .orElseThrow(() -> new TeacherNotFoundException("error.findTeacher"));
    }

    @Override
    public List<TeacherEntity> searchByName(String namePart) {
        return teacherRepository.findByFullNameContaining(namePart)
                .stream()
                .filter(teacherEntity -> !teacherEntity.getActive().equals(Active.DELETED))
                .toList();
    }

    @Override
    public List<TeacherEntity> getByDateBerth(LocalDate dateBerth) {
        return teacherRepository.findAllByDateBerth(dateBerth)
                .stream()
                .filter(teacherEntity -> !teacherEntity.getActive().equals(Active.DELETED))
                .toList();
    }

    @Override
    public List<TeacherEntity> getByStatusActive(Active status) {
        return teacherRepository.findAllByActive(status);
    }

    @Override
    public List<TeacherEntity> getByPortfolio(String department) {
        return teacherRepository.findAllByLinkPortfolio(department)
                .stream()
                .filter(teacherEntity -> !teacherEntity.getActive().equals(Active.DELETED))
                .toList();
    }

    @Override
    public List<TeacherEntity> getByDateEmployment(LocalDate dateEmployment) {
        return teacherRepository.findAllByDateEmployment(dateEmployment)
                .stream()
                .filter(teacherEntity -> !teacherEntity.getActive().equals(Active.DELETED))
                .toList();
    }

    @Override
    public List<TeacherEntity> getDateDismissal(LocalDate dateDismissal) {
        return teacherRepository.findAllByDateDismissal(dateDismissal)
                .stream()
                .filter(teacherEntity -> !teacherEntity.getActive().equals(Active.DELETED))
                .toList();
    }

    @Override
    public TeacherEntity update(TeacherEntity entity) {
        TeacherEntity existing = teacherRepository.findById(entity.getId())
                .orElseThrow(() -> new TeacherNotFoundException("error.findTeacher"));

        if (existing.getActive().equals(Active.DELETED)) {
            throw new ValidationError("error.NotUpdateDeleteTeacher");
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
