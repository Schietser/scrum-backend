package com.example.scrumtrial.models.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Optional;

@Accessors(fluent = true)
@Data
public class LoginReply {
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Optional<String> sessionToken = Optional.empty();
    private Boolean success = false;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Optional<String> error = Optional.empty();
}
