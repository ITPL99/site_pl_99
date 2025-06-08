package com.example.site_pl_99.repository;

import com.example.site_pl_99.entity.MailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MailRepository extends JpaRepository<MailEntity, Long> {
    Optional<MailEntity> findByMailName(String mailName);
}
