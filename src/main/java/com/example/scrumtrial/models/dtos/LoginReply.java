package com.example.scrumtrial.models.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Optional;

@Accessors(fluent = true)
@Data
public class LoginReply {
    private Optional<String> sessionToken = Optional.empty();
    private Boolean success = false;
    private Optional<String> error = Optional.empty();
}
