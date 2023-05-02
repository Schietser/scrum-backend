package com.example.scrumtrial.models.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Optional;

@Accessors(fluent = true)
@Getter
@Setter
public class LoginReply {
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Optional<String> sessionToken = Optional.empty();
    private Boolean success = false;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Optional<String> error = Optional.empty();

    // TODO: MAP ALL LOGINREPLY CREATIONS TO THIS CONSTRUCTOR
    // SUCCESS BEING NULL IS LOGICALLY PROBLEMATIC
    public LoginReply(boolean success){
        this.success = success;
    }
}
