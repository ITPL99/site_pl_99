package com.example.site_pl_99.service;

import com.example.site_pl_99.entity.Worker;

import java.util.List;

public interface WorkerService {
    Worker save(Worker worker);
    List<Worker> getAll();
    Worker getWorkerById(Long id);
}
