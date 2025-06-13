package com.example.site_pl_99.repository;

import com.example.site_pl_99.entity.MessageEntity;
import com.example.site_pl_99.entity.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageStatusRepository extends JpaRepository<MessageStatus, Long> {
    Optional<MessageStatus> findByStatusName(String statusName);
}
