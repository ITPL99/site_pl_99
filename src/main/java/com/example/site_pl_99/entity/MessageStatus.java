package com.example.site_pl_99.entity;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@Table(name = "status")
@RequiredArgsConstructor
public class MessageStatus extends BaseEntity{
    @Column(name = "status_name")
    private String statusName;
    @OneToMany(mappedBy = "messageStatus")
    private List<MessageEntity> messageEntity;

    public String getStatusName() {
        return statusName;
    }

    public MessageStatus setStatusName(String statusName) {
        this.statusName = statusName;
        return this;
    }

    public List<MessageEntity> getMessageEntity() {
        return messageEntity;
    }

    public MessageStatus setMessageEntity(List<MessageEntity> messageEntity) {
        this.messageEntity = messageEntity;
        return this;
    }
}
