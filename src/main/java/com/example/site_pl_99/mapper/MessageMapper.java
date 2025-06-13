package com.example.site_pl_99.mapper;

import com.example.site_pl_99.dto.MessageDtoRequest;
import com.example.site_pl_99.dto.MessageDtoResponse;
import com.example.site_pl_99.entity.MessageEntity;
import com.example.site_pl_99.entity.MessageStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MessageMapper {
    public static MessageDtoResponse toDtoResponse(MessageEntity messageEntity) {
        MessageDtoResponse messageDtoResponse = new MessageDtoResponse();
        messageDtoResponse.setId(messageEntity.getId())
                .setFullName(messageEntity.getFullName())
                .setTitle(messageEntity.getTitle())
                .setMessage(messageEntity.getMessage())
                .setDateCreated(messageEntity.getDateCreated())
                .setDateUpdated(messageEntity.getDateUpdated())
                .setMessageStatus(messageEntity.getMessageStatus().getStatusName());
        if(Objects.nonNull(messageEntity.getMail())) messageDtoResponse.setMailName(messageEntity.getMail());
        return messageDtoResponse;
    }

    public static MessageEntity toEntity(MessageDtoRequest messageDtoRequest) {
        return new MessageEntity().setTitle(messageDtoRequest.getTitle())
                .setFullName(messageDtoRequest.getFullName())
                .setMessage(messageDtoRequest.getMessage());
    }

    public static List<MessageDtoResponse> toDtoResponseList(List<MessageEntity> messageEntityList) {
        return messageEntityList.stream().map(MessageMapper::toDtoResponse).collect(Collectors.toList());
    }
}
