package com.example.site_pl_99.entity;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "mails")
@RequiredArgsConstructor
public class MailEntity extends BaseEntity {
    @Column(name = "mail_name")
    private String mailName;
    @Column(name = "date_created")
    private LocalDateTime dateCreated;
    @Column(name = "date_updated")
    private LocalDateTime dateUpdated;
    @OneToMany(mappedBy = "mailEntity", fetch = FetchType.EAGER)
    private List<MessageEntity> messageEntityList;

    public String getMailName() {
        return mailName;
    }

    public MailEntity setMailName(String mailName) {
        this.mailName = mailName;
        return this;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public MailEntity setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public MailEntity setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
        return this;
    }

    public List<MessageEntity> getMessageEntityList() {
        return messageEntityList;
    }

    public MailEntity setMessageEntityList(List<MessageEntity> messageEntityList) {
        this.messageEntityList = messageEntityList;
        return this;
    }
}
