package com.example.site_pl_99.entity;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@RequiredArgsConstructor
public class MessageEntity extends BaseEntity {
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "title")
    private String title;
    @Column(name = "message")
    private String message;
    @Column(name = "date_created")
    private LocalDateTime dateCreated;
    @Column(name = "date_updated")
    private LocalDateTime dateUpdated;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mail_id", referencedColumnName = "id")
    private MailEntity mailEntity;

    @PrePersist
    public void prePersist() {
        if(fullName == null) fullName = "anonim";
        dateCreated = LocalDateTime.now();
        dateUpdated = LocalDateTime.now();
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

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public MessageEntity setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
        return this;
    }

    public MailEntity getMailEntity() {
        return mailEntity;
    }

    public MessageEntity setMailEntity(MailEntity mailEntity) {
        this.mailEntity = mailEntity;
        return this;
    }
}
