package com.example.site_pl_99.repository;

import com.example.site_pl_99.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {
    Optional<List<Worker>> findWorkerByName(String name);
}
