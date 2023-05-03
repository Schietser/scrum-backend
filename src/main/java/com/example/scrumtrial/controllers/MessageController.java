package com.example.scrumtrial.controllers;

import com.example.scrumtrial.Flow.Services.EmailService;
import com.example.scrumtrial.Flow.Services.MessageService;
import com.example.scrumtrial.Flow.Services.UserService;
import com.example.scrumtrial.models.dtos.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/v1/messages")
@CrossOrigin(origins = "http://localhost:4200")
public class MessageController {
    private  final Random r = new Random();
    private final MessageService ms;
    private final EmailService es;

    public MessageController(UserService us, MessageService ms, EmailService es){
        this.ms = ms;
        this.es = es;
    }

    @PostMapping("/sendEmail")
    public void sendEmail(@RequestBody SendEmailReq req){
        this.es.sendEmail(req.getFrom(), req.getTo(), req.getSubject(), req.getBody());
    }

    @PostMapping("/addMsg")
    public void postNewMessage(@RequestBody NewMsgEmailToEmailReq req){
        ms.postMessage(req);
    }

    @GetMapping("/usr/from/email")
    public List<MessageResponse> getMessagesSentByUsr(@RequestBody MsgByEmailRequest req){
        return ms.getAllSentBy(req);
    }

    @GetMapping("/usr/from/sms")
    public List<MessageResponse> getMessagesSentByUsr(@RequestBody MsgByPhoneRequest req){
        return ms.getAllSentBy(req);
    }


    @GetMapping("/usr/to/email")
    public List<MessageResponse> getMessagesReceivedByUsr(@RequestBody MsgToEmailRequest req){ return ms.getAllReceivedBy(req);}

    @GetMapping("/usr/to/sms")
    public List<MessageResponse> getMessagesReceivedByUsr(@RequestBody MsgToPhoneRequest req){ return ms.getAllReceivedBy(req);}

    /*
    @GetMapping("/")
    public String user(@CookieValue("loginCred") Integer hash){
        UserEntity tue = new UserEntity(f.name().username(), f.name().nameWithMiddle());
        ur.save(tue);
        return "sucess";
    }

    @GetMapping("/m")
    public String msg(){
        List<UserEntity> usrs = ur.findAll();
        List<UserEntity> toUsers = new LinkedList<>();
        UserEntity fu = usrs.get(r.nextInt(usrs.size()));
        for (int i = r.nextInt(usrs.size()/2); i < r.nextInt(usrs.size()/2, usrs.size()); i++) {
            toUsers.add(usrs.get(i));
        }
        mr.save(new MessageEntity(fu, toUsers, f.lorem().sentence()));
        return "sucess";
    }
    */
}
