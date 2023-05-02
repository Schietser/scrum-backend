package com.example.scrumtrial.Services;

import com.example.scrumtrial.models.dtos.MessageResponse;
import com.example.scrumtrial.models.dtos.MsgByEmailRequest;
import com.example.scrumtrial.models.dtos.MsgBySmsRequest;
import com.example.scrumtrial.models.dtos.MsgToSmsRequest;
import com.example.scrumtrial.models.dtos.MsgToEmailRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    public List<MessageResponse> getAllSentBy(MsgByEmailRequest req) {
        System.err.println("getAllSentBy (email) is not yet implemented");
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