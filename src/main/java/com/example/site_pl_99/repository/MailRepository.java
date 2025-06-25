package com.example.site_pl_99.repository;

import com.example.site_pl_99.entity.MailEntity;
import com.example.site_pl_99.enums.StatusMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MailRepository extends JpaRepository<MailEntity, Long> {
    List<MailEntity> findByTitle(String title);
    List<MailEntity> findByStatusMail(StatusMessage statusMail);
}
