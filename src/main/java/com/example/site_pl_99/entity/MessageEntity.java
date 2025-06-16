package com.example.site_pl_99.entity;

import com.example.site_pl_99.enums.StatusMessage;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@RequiredArgsConstructor
public class MessageEntity extends BaseEntity {
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "message", nullable = false)
    private String message;
    @Column(name = "date_created")
    private LocalDateTime dateCreated;
    @Column(name = "email", nullable = false)
    private String toEmail;
    @Column(name = "current_status", nullable = false)
    private StatusMessage currentStatus;

    @PrePersist
    public void prePersist() {
        currentStatus = StatusMessage.CONSIDER;
        dateCreated = LocalDateTime.now();
    }

    public String getFullName() {
        return fullName;
    }

    public MessageEntity setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public MessageEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public MessageEntity setMessage(String message) {
        this.message = message;
        return this;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public MessageEntity setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public String getToEmail() {
        return toEmail;
    }

    public MessageEntity setToEmail(String toEmail) {
        this.toEmail = toEmail;
        return this;
    }

    public StatusMessage getCurrentStatus() {
        return currentStatus;
    }

    public MessageEntity setCurrentStatus(StatusMessage currentStatus) {
        this.currentStatus = currentStatus;
        return this;
    }
}
