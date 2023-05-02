package com.example.scrumtrial.Services;

import com.example.scrumtrial.models.dtos.MessageResponse;
import com.example.scrumtrial.models.dtos.MsgByEmailRequest;
import com.example.scrumtrial.models.dtos.MsgBySmsRequest;
import com.example.scrumtrial.models.dtos.MsgToSmsRequest;
import com.example.scrumtrial.models.dtos.MsgToEmailRequest;
import com.example.scrumtrial.models.entities.UserEntity;
import com.example.scrumtrial.models.repositories.MessageRepository;
import com.example.scrumtrial.models.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        if(ue.isEmpty()){return List.of();}
        mr.findAllByFrom(ue.get());
        return List.of();
    }

    public List<MessageResponse> getAllSentBy(MsgBySmsRequest req) {
        System.err.println("getAllSentBy (sms) is not yet implemented");
        return List.of();
    }

    public List<MessageResponse> getAllReceivedBy(MsgToSmsRequest req){
        System.err.println("getAllSentBy (sms) is not yet implemented");
        return List.of();
    }

    public List<MessageResponse> getAllReceivedBy(MsgToEmailRequest  req){
        System.err.println("getAllSentBy (sms) is not yet implemented");
        return List.of();
    }
}
