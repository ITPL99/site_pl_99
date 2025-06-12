package com.example.site_pl_99.entity;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import lombok.Value;

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
    @Column(name = "mail")
    private String mail;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "current_status", referencedColumnName = "id")
    private MessageStatus messageStatus;

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

    public MessageStatus getMessageStatus() {
        return messageStatus;
    }

    public MessageEntity setMessageStatus(MessageStatus messageStatus) {
        this.messageStatus = messageStatus;
        return this;
    }

    public String getMail() {
        return mail;
    }

    public MessageEntity setMail(String mail) {
        this.mail = mail;
        return this;
    }
}
