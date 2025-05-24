package com.example.site_pl_99.service.impl;

import com.example.site_pl_99.entity.Qualification;
import com.example.site_pl_99.entity.Worker;
import com.example.site_pl_99.repository.CourseRepository;
import com.example.site_pl_99.repository.QualificationRepository;
import com.example.site_pl_99.repository.WorkerRepository;
import com.example.site_pl_99.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {

    private final WorkerRepository workerRepository;
    private final QualificationRepository qualificationRepository;

    @Autowired
    public WorkerServiceImpl(WorkerRepository workerRepository, QualificationRepository qualificationRepository) {
        this.workerRepository = workerRepository;
        this.qualificationRepository = qualificationRepository;
    }

    @Override
    public Worker save(Worker worker) {
        for(Qualification qualification : worker.getQualifications()) {
            qualificationRepository.save(qualification);
        }
        workerRepository.save(worker);
        return worker;
    }

    @Override
    public List<Worker> getAll() {
        return workerRepository.findAll();
    }

    @Override
    public Worker getWorkerById(Long id) {
        return workerRepository.findById(id).orElseThrow(() -> new RuntimeException("Такого работника нет"));
    }
}
