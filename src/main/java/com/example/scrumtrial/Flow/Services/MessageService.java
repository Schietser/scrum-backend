package com.example.scrumtrial.Flow.Services;

import com.example.scrumtrial.Flow.Mappers.MsgMapper;
import com.example.scrumtrial.models.dtos.*;
import com.example.scrumtrial.models.entities.MessageEntity;
import com.example.scrumtrial.models.entities.UserEntity;
import com.example.scrumtrial.models.repositories.MessageRepository;
import com.example.scrumtrial.models.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MessageService {
    private final MessageRepository mr;
    private final UserRepository ur;

    public MessageService(MessageRepository mr, UserRepository ur){
        this.mr = mr;
        this.ur = ur;
    }

    public List<MessageResponse> getAllSentBy(MsgByEmailRequest req) {
        Optional<UserEntity> ue = ur.findUserEntityByEmail(req.getEmail());
        return ue.map(user -> mr.findAllByFrom(user).stream().map(MsgMapper::toResponse).collect(Collectors.toList())).orElseGet(List::of);
    }

    public List<MessageResponse> getAllSentBy(MsgBySmsRequest req) {
        Optional<UserEntity> ue = ur.findUserEntityBySms(req.getSms());
        return ue.map(user -> mr.findAllByFrom(user).stream().map(MsgMapper::toResponse).collect(Collectors.toList())).orElseGet(List::of);
    }

    public List<MessageResponse> getAllReceivedBy(MsgToSmsRequest req){
        Optional<UserEntity> ue = ur.findUserEntityBySms(req.getSms());
        return ue.map(user -> mr.findAllByToContains(user).stream().map(MsgMapper::toResponse).collect(Collectors.toList())).orElseGet(List::of);
    }

    public List<MessageResponse> getAllReceivedBy(MsgToEmailRequest req){
        Optional<UserEntity> ue = ur.findUserEntityBySms(req.getEmail());
        return ue.map(user -> mr.findAllByToContains(user).stream().map(MsgMapper::toResponse).collect(Collectors.toList())).orElseGet(List::of);
    }

    public void postMessage(NewMsgEmailToEmailReq req) {
        Optional<UserEntity> ue = ur.findUserEntityByEmail(req.getSenderEmail());
        List<UserEntity> re = req.getReceiverEmails().stream()
                .map(ur::findUserEntityByEmail)
                .map(e -> e.orElse(null))
                .filter(Objects::nonNull)
        .collect(Collectors.toList());
        if(ue.isEmpty() || re.isEmpty()){return;}
        mr.save(new MessageEntity(ue.get(), re, req.getContent()));
    }
}
