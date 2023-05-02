package com.example.scrumtrial.models.dtos;

import lombok.Data;

import java.util.List;

@Data
public class NewMsgEmailToEmailReq {
    private String content;
    private String senderEmail;
    private List<String> receiverEmails;
}
