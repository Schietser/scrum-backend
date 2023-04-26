package com.example.scrumtrial.models.dtos;

import lombok.Data;

import java.util.List;

@Data
public class MessageResponse {
    private String sender;
    private List<String> receivers;
    private String body;
}
