package com.example.site_pl_99.repository;

import com.example.site_pl_99.entity.MessageEntity;
import com.example.site_pl_99.entity.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    List<MessageEntity> findAllByMessageStatus(MessageStatus messageStatus);
}
