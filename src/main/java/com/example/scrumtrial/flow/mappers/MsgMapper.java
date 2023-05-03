package com.example.scrumtrial.flow.mappers;

import com.example.scrumtrial.models.dtos.MessageResponse;
import com.example.scrumtrial.models.entities.MessageEntity;
import com.example.scrumtrial.models.entities.UserEntity;

import java.util.stream.Collectors;

public class MsgMapper {
    public static MessageResponse toResponse(MessageEntity msg){
        MessageResponse m = new MessageResponse();
        m.setBody(msg.getContent());
        m.setSender(msg.getFrom().getName());
        m.setReceivers(msg.getTo().stream().map(UserEntity::getName).collect(Collectors.toList()));
        return m;
    }
}
