package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.entity.TeacherEntity;
import com.example.site_pl_99.enums.Active;
import com.example.site_pl_99.excaption.UserNotFoundException;
import com.example.site_pl_99.repository.TeacherRepository;
import com.example.site_pl_99.service.TeacherService;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public TeacherEntity getFullName(String fullName) {
        return teacherRepository.findByFullName(fullName).orElseThrow(() -> new NotFoundException("Учитель  не найден"));
    }

    @Override
    public List<TeacherEntity> getAllMastersContentName(String fullName) {
        return teacherRepository.findByFullNameContaining(fullName).orElseThrow(() -> new NotFoundException("Учителя не найдены"));
    }

    @Override
    public List<TeacherEntity> getAllMastersByDateBerth(LocalDate dateBerth) {
        return teacherRepository.findAllByDateBerth(dateBerth).orElseThrow(() -> new NotFoundException("Учитель не найден"));
    }

    @Override
    public List<TeacherEntity> getAllMastersByStatusActive(Active status) {
        return teacherRepository.findAllByActive(status).orElseThrow(() -> new NotFoundException("Работник с данным статусом не найден"));
    }

    @Override
    public List<TeacherEntity> getAllMastersByPortfolio(String department) {
        return teacherRepository.findAllByLinkPortfolio(department).orElseThrow(() -> new NotFoundException("Учитель не найден"));
    }

    @Override
    public List<TeacherEntity> getAllMastersByDateEmployment(LocalDate dateEmployment) {
        return teacherRepository.findAllByDateEmployment(dateEmployment).orElseThrow(() -> new UserNotFoundException("Учитель не найден"));
    }

    @Override
    public List<TeacherEntity> getAllMastersByDateDismissal(LocalDate dateDismissal) {
        return teacherRepository.findAllByDateDismissal(dateDismissal).orElseThrow(() -> new NotFoundException("Учитель не найден"));
    }

    @Override
    public TeacherEntity getById(Long id) {
        return teacherRepository.findById(id).orElseThrow(() -> new NotFoundException("Не найден"));
    }

    @Override
    public TeacherEntity save(TeacherEntity entity) {
        return teacherRepository.save(entity);
    }

    @Override
    public List<TeacherEntity> getAll() {
        return teacherRepository.findAll().stream().filter(t-> t.getActive() == Active.ACTIVE ).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        TeacherEntity teacherEntity = getById(id);
        teacherEntity.setActive(Active.DELETED);
        teacherRepository.save(teacherEntity);
    }
}
