package com.example.site_pl_99.entity;

import com.example.site_pl_99.enums.StatusMessage;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "mails")
@RequiredArgsConstructor
public class MailEntity extends BaseEntity {
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "email_to", nullable = false)
    private String emailTo;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusMessage statusMail;
    @Column(name = "date_created")
    private LocalDate dateCreated;

    @PrePersist
    public void prePersist() {
        dateCreated = LocalDate.now();
        statusMail = StatusMessage.CONSIDER;
    }

    public String getTitle() {
        return title;
    }

    public MailEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public MailEntity setContent(String content) {
        this.content = content;
        return this;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public MailEntity setEmailTo(String emailTo) {
        this.emailTo = emailTo;
        return this;
    }

    public StatusMessage getStatusMail() {
        return statusMail;
    }

    public MailEntity setStatusMail(StatusMessage statusMail) {
        this.statusMail = statusMail;
        return this;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public MailEntity setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }
}
